// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.symbols;

import java.util.ArrayList;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleSymbolException;
import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.EagleScope;

public class Perl_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(EagleLanguage language)
	{
		Perl_Program program = (Perl_Program) language;
		resolveFunctionReferences(program);
		//resolveVariableReferences(program);
	}
	
	private void resolveFunctionReferences(Perl_Program program)
	{
		EagleScope scope = program.getScope();
		ArrayList<AbstractToken> functionDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(functionDefinitions, program,
				Perl_Function_Definition.class);

		ArrayList<AbstractToken> functionReferences = new ArrayList<AbstractToken>();
		findAllInstances(functionReferences, program,
				Perl_Identifier_Reference.class);
		
		for (AbstractToken defToken : functionDefinitions)
		{
			Perl_Function_Definition def = (Perl_Function_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Function definition for " + def + " at " +
						(def._currentLine+1) + "/" + (def._currentChar+1));
			}
		}

		// Match 'em up!
		for (AbstractToken refToken : functionReferences)
		{
			Perl_Identifier_Reference ref = (Perl_Identifier_Reference) refToken;
			int foundAny = 0;
			
			for (AbstractToken defToken : functionDefinitions)
			{
				Perl_Function_Definition function = (Perl_Function_Definition) defToken;
				String functionName = function.toString();
				if (ref.toString().equalsIgnoreCase(functionName))
				{
					if (foundAny == 1) System.err.println("**** Duplicate function definition for " + functionName);
					if (_trace) System.out.println("Function reference to " + functionName + " at " +
							(ref._currentLine+1) + "/" + (ref._currentChar+1));
					ref.setDefinition(function);
					function.addReference(ref);
					foundAny++;
				}
			}
			if (foundAny == 0)
			{
				throw new EagleSymbolException("*** Unable to find a Function Definition for " + ref);
			}
		}
	}
}
