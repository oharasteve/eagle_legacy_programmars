// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.symbols;

import java.util.ArrayList;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleProject;
import com.eagle.core.EagleSyntax;
import com.eagle.core.ProgramEntry;
import com.eagle.programmar.CSharp.CSharp_Method;
import com.eagle.programmar.CSharp.CSharp_Program;
import com.eagle.programmar.CSharp.CSharp_Program.CSharp_Using;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Symbols.CSharp_Class_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.EagleScope;

public class CSharp_Resolve_References extends Eagle_Resolve_References
{
	public void resolveLocalReferences(EagleLanguage language)
	{
		CSharp_Program program = (CSharp_Program) language;
		EagleScope scope = program.getScope();
		EagleSyntax syntax = program.getSyntax();
		
		// Handle all the globally scoped references
		connectReferences(syntax, scope, program, CSharp_Class_Definition.class, CSharp_Identifier_Reference.class);
		connectReferences(syntax, scope, program, CSharp_Method_Definition.class, CSharp_Identifier_Reference.class);
		connectReferencesOutside(syntax, scope, program, CSharp_Variable_Definition.class, CSharp_Identifier_Reference.class, CSharp_Method.class);
		
		// Find all the methods in the program
		ArrayList<AbstractToken> methods = findAllInstances(program, CSharp_Method.class);
		for (AbstractToken meth : methods)
		{
			// Handle all the method-scoped references
			connectReferences(syntax, scope, meth, CSharp_Variable_Definition.class, CSharp_Identifier_Reference.class);
		}

		// Find all the block statements in the program
		ArrayList<AbstractToken> blocks = findAllInstances(program, CSharp_StatementBlock.class);
		for (AbstractToken blk : blocks)
		{
			// Handle all the method-scoped references
			connectReferences(syntax, scope, blk, CSharp_Variable_Definition.class, CSharp_Identifier_Reference.class);
		}
	}

	// Connect up all the external references from this program
	public void resolveExternalReferences(EagleProject project, EagleLanguage language)
	{
		CSharp_Program program = (CSharp_Program) language;

		// Collect all the import statements
		ArrayList<AbstractToken> imports = findAllInstances(program, CSharp_Using.class);
		for (AbstractToken imp : imports)
		{
			CSharp_Using jimport = (CSharp_Using) imp;
			
			CSharp_Identifier firstId = jimport.id.getPrimaryElement(0);
			String name = firstId.getValue();
			//TokenList<CSharp_DotIdentifier> dotIds = jimport.dotId;
			//for (CSharp_DotIdentifier dotId : dotIds)
			//{
			//	if (dotId.idStar.whichToken instanceof CSharp_Identifier)
			//	name += "/" + ((CSharp_Identifier) dotId.idStar.whichToken).getValue();
			//}
			
			ProgramEntry yours = (ProgramEntry) project.findEntry(name);
			if (yours == null) continue;	// Couldn't find it, maybe it didn't parse?
			CSharp_Program program2 = (CSharp_Program) project.loadProgramFromXML(yours);

			// Find all the unresolved class references
			ArrayList<AbstractToken> classes = findAllInstances(program, CSharp_Identifier_Reference.class);
			for (AbstractToken cls : classes)
			{
				CSharp_Identifier_Reference ref = (CSharp_Identifier_Reference) cls;
				if (ref.searchForDefinition() == null)
				{
					// Maybe it is defined in program2
					ArrayList<AbstractToken> classes2 = findAllInstances(program2, CSharp_Class_Definition.class);
					for (AbstractToken cls2 : classes2)
					{
						CSharp_Identifier_Reference def = (CSharp_Identifier_Reference) cls2;
						if (ref.getValue().equals(def.getValue()))
						{
							System.out.println("*** Found a definition for " + ref.getValue() + " in " + yours.sourceFile);
						}
					}
				}
			}
		}
	}
}
