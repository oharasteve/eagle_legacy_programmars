// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.symbols;

import java.util.ArrayList;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Natural.Natural_Program;
import com.eagle.programmar.Natural.Symbols.Natural_Data_Definition;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.AbstractToken;

public class Natural_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(EagleLanguage language)
	{
		Natural_Program program = (Natural_Program) language;
		resolveDataReferences(program);
	}

	private void resolveDataReferences(Natural_Program program)
	{
		EagleScope scope = program.getScope();
		ArrayList<AbstractToken> defineStatements = new ArrayList<AbstractToken>();
		findAllInstances(defineStatements, program, Natural_Data_Definition.class);

		ArrayList<AbstractToken> dataReferences = new ArrayList<AbstractToken>();
		findAllInstances(dataReferences, program, Natural_Identifier_Reference.class);

		for (AbstractToken defToken : defineStatements)
		{
			Natural_Data_Definition def = (Natural_Data_Definition) defToken;
			scope.addSymbol(def);
		}

		// Match 'em up!
		for (AbstractToken refToken : dataReferences)
		{
			Natural_Identifier_Reference ref = (Natural_Identifier_Reference) refToken;
			int foundAny = 0;
			for (AbstractToken defToken : defineStatements)
			{
				Natural_Data_Definition def = (Natural_Data_Definition) defToken;
				// System.out.println("*** def = " + def.toString());
				if (ref.toString().equalsIgnoreCase(def.toString()))
				{
					if (foundAny == 1) System.err.println("**** Duplicate data definition for " + ref);
					if (_trace) System.out.println("Data reference to " + ref + " at " + (ref.getStartLine() + 1) + "/"
							+ (ref.getStartChar() + 1));
					ref.setDefinition(def);
					def.addReference(ref);
					foundAny++;
				}
			}

			if (foundAny == 0)
			{
				System.err.println("**** Unable to find a Data Definition for " + ref);
			}
		}
	}
}
