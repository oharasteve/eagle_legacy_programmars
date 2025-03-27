// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.symbols_UNUSED;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.DefinitionInterface;

public class Javascript_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(AbstractLanguage language)
	{
		Javascript_Program program = (Javascript_Program) language;
		EagleScope scope = program.getScope();

		// Resolve all the function references
		resolveFunctionReferences(program, scope);

		// Find all the functions
		ArrayList<AbstractToken> functions = new ArrayList<AbstractToken>();
		findAllInstances(functions, program, Javascript_Function.class);

		// Now attach all local variables to their surrounding functions
		for (AbstractToken token : functions)
		{
			Javascript_Function fn = (Javascript_Function) token;
			findVariablesScope(fn);
			// System.out.println("&&&&&&&& Processing " + fn);
		}

		// And finally, resolve all the variable references
		resolveVariableReferences(program, scope);
	}

	private void resolveFunctionReferences(Javascript_Program program, EagleScope scope)
	{
		ArrayList<AbstractToken> functionDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(functionDefinitions, program, Javascript_Function_Definition.class);

		ArrayList<AbstractToken> functionReferences = new ArrayList<AbstractToken>();
		findAllInstances(functionReferences, program, Javascript_Identifier_Reference.class);

		for (AbstractToken defToken : functionDefinitions)
		{
			Javascript_Function_Definition def = (Javascript_Function_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Function definition for " + def + " at " + (def.getStartLine() + 1) + "/"
						+ (def.getStartChar() + 1));
			}
		}

		// Match 'em up!
		for (AbstractToken refToken : functionReferences)
		{
			Javascript_Identifier_Reference ref = (Javascript_Identifier_Reference) refToken;
			int foundAny = 0;

			for (AbstractToken defToken : functionDefinitions)
			{
				Javascript_Function_Definition function = (Javascript_Function_Definition) defToken;
				String functionName = function.toString();
				if (ref.toString().equals(functionName))
				{
					if (foundAny == 1) System.err.println("**** Duplicate function definition for " + functionName);
					if (_trace) System.out.println("Function reference to " + functionName + " at "
							+ (ref.getStartLine() + 1) + "/" + (ref.getStartChar() + 1));
					ref.setDefinition(function);
					function.addReference(ref);
					foundAny++;
				}
			}
			if (foundAny == 0)
			{
				System.err.println("*** Unable to find a Function Definition for " + ref);
			}
		}
	}

	private void findVariablesScope(Javascript_Function fn)
	{
		ArrayList<AbstractToken> variableDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(variableDefinitions, fn, Javascript_Variable_Definition.class);
		/*
		 * for (AbstractToken defToken : variableDefinitions) {
		 * Javascript_Variable_Definition def = (Javascript_Variable_Definition)
		 * defToken; def.containingFunction = fn; //
		 * System.out.println("&&&&&&&& Definition " + def + " is in " + fn); }
		 * 
		 * ArrayList<AbstractToken> variableReferences = new ArrayList<AbstractToken>();
		 * EagleUtilities.findAllInstances(variableReferences, fn,
		 * Javascript_Variable_Reference.class); for (AbstractToken refToken :
		 * variableReferences) { Javascript_Variable_Reference ref =
		 * (Javascript_Variable_Reference) refToken; ref.containingFunction = fn; //
		 * System.out.println("&&&&&&&& Reference " + ref + " is in " + fn); }
		 */
	}

	private void resolveVariableReferences(Javascript_Program program, EagleScope scope)
	{
		ArrayList<AbstractToken> variableDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(variableDefinitions, program, Javascript_Variable_Definition.class);

		ArrayList<AbstractToken> variableReferences = new ArrayList<AbstractToken>();
		findAllInstances(variableReferences, program, Javascript_Identifier_Reference.class);

		for (AbstractToken defToken : variableDefinitions)
		{
			Javascript_Variable_Definition def = (Javascript_Variable_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Variable definition for " + def + " at " + (def.getStartLine() + 1) + "/"
						+ (def.getStartChar() + 1));
			}
		}

		// Match 'em up!
		for (AbstractToken refToken : variableReferences)
		{
			Javascript_Identifier_Reference ref = (Javascript_Identifier_Reference) refToken;
			DefinitionInterface def = ref.searchForDefinition();
			if (def != null) continue; // Already found a definition for this

			int foundAny = 0;
			for (AbstractToken defToken : variableDefinitions)
			{
				Javascript_Variable_Definition variable = (Javascript_Variable_Definition) defToken;
				String variableName = variable.toString();
				if (ref.toString().equals(variableName))// && variable.containingFunction == ref.containingFunction)
				{
					if (foundAny == 1) System.err.println("**** Duplicate variable definition for " + variableName);
					if (_trace) System.out.println("Variable reference to " + variableName + " at "
							+ (ref.getStartLine() + 1) + "/" + (ref.getStartChar() + 1));
					ref.setDefinition(variable);
					variable.addReference(ref);
					foundAny++;
				}
			}

			// Check for global ones next
			if (foundAny == 0)
			{
				for (AbstractToken defToken : variableDefinitions)
				{
					Javascript_Variable_Definition variable = (Javascript_Variable_Definition) defToken;
					String variableName = variable.toString();
					if (ref.toString().equals(variableName))// && variable.containingFunction == null)
					{
						if (foundAny == 1) System.err.println("**** Duplicate variable definition for " + variableName);
						if (_trace) System.out.println("Variable reference to " + variableName + " at "
								+ (ref.getStartLine() + 1) + "/" + (ref.getStartChar() + 1));
						ref.setDefinition(variable);
						variable.addReference(ref);
						foundAny++;
					}
				}
			}

			// Well, maybe use the first reference as the definition I guess. But how?

			// Give up
			if (foundAny == 0)
			{
				System.err.println("*** Unable to find a Variable Definition for " + ref);
			}
		}
	}
}
