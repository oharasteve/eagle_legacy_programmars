// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.symbols_UNUSED;

import java.util.ArrayList;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleProject;
import com.eagle.core.EagleSyntax;
import com.eagle.core.ProgramEntry;
import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Java_Program;
import com.eagle.programmar.Java.Java_Program.Java_Import;
import com.eagle.programmar.Java.Java_Program.Java_Import.Java_DotIdentifierStar;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Symbols.Java_Class_Definition;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Symbols.Java_Method_Definition;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;

public class Java_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(EagleLanguage language)
	{
		Java_Program program = (Java_Program) language;
		EagleScope scope = program.getScope();
		EagleSyntax syntax = program.getSyntax();

		// Handle all the globally scoped references
		connectReferences(syntax, scope, program, Java_Class_Definition.class, Java_Identifier_Reference.class);
		connectReferences(syntax, scope, program, Java_Method_Definition.class, Java_Identifier_Reference.class);
		connectReferencesOutside(syntax, scope, program, Java_Variable_Definition.class,
				Java_Identifier_Reference.class, Java_Method.class);

		// Find all the methods in the program
		ArrayList<AbstractToken> methods = findAllInstances(program, Java_Method.class);
		for (AbstractToken meth : methods)
		{
			// Handle all the method-scoped references
			connectReferences(syntax, scope, meth, Java_Variable_Definition.class, Java_Identifier_Reference.class);
		}

		// Find all the block statements in the program
		ArrayList<AbstractToken> blocks = findAllInstances(program, Java_StatementBlock.class);
		for (AbstractToken blk : blocks)
		{
			// Handle all the method-scoped references
			connectReferences(syntax, scope, blk, Java_Variable_Definition.class, Java_Identifier_Reference.class);
		}
	}

	// Connect up all the external references from this program
	public void resolveExternalReferences(EagleProject project, EagleLanguage language)
	{
		Java_Program program = (Java_Program) language;

		// Collect all the import statements
		ArrayList<AbstractToken> imports = findAllInstances(program, Java_Import.class);
		for (AbstractToken imp : imports)
		{
			Java_Import jimport = (Java_Import) imp;

			Java_Identifier firstId = jimport.id;
			String name = firstId.getValue();
			TokenList<Java_DotIdentifierStar> dotIds = jimport.dotId;
			for (Java_DotIdentifierStar dotId : dotIds._elements)
			{
				AbstractToken which = dotId.idStar.getWhich();
				if (which instanceof Java_Identifier) name += "/" + ((Java_Identifier) which).getValue();
			}

			ProgramEntry yours = (ProgramEntry) project.findEntry(name);
			if (yours == null) continue; // Couldn't find it, maybe it didn't parse?
			Java_Program program2 = (Java_Program) project.loadProgramFromXML(yours);

			// Find all the unresolved class references
			ArrayList<AbstractToken> classes = findAllInstances(program, Java_Identifier_Reference.class);
			for (AbstractToken cls : classes)
			{
				Java_Identifier_Reference ref = (Java_Identifier_Reference) cls;
				if (ref.searchForDefinition() == null)
				{
					// Maybe it is defined in program2
					ArrayList<AbstractToken> classes2 = findAllInstances(program2, Java_Class_Definition.class);
					for (AbstractToken cls2 : classes2)
					{
						Java_Identifier_Reference def = (Java_Identifier_Reference) cls2;
						if (ref.getValue().equals(def.getValue()))
						{
							System.out.println(
									"*** Found a definition for " + ref.getValue() + " in " + yours.sourceFile);
						}
					}
				}
			}
		}
	}
}
