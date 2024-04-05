// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Function_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Parameter_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Function_Definition;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_Identifier_Reference name;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Eaglish_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (interpreter._TRACE) System.err.println("*** Calling " + name + "()");
		
		// Search for the function definition
		Eaglish_Function_Definition def = (Eaglish_Function_Definition) name.findDefinitionInScope();
		if (def == null)
		{
			throw new RuntimeException("No function called " + name);
		}
		AbstractToken parent = def.getParent();
		if (! (parent instanceof Eaglish_Function_Block))
		{
			throw new RuntimeException("Cannot call " + name + " becuase it is not a Function");
		}
		Eaglish_Function_Block func = (Eaglish_Function_Block) parent;
		
		// Make sure the function args match up
		if (! func.returnsStatement.isPresent())
		{
			throw new RuntimeException("Function " + name + " doesn't return any value");
		}
		int argCount = args.getPrimaryCount();
		int paramCount = func.parameterStatements.size();
		if (argCount != paramCount)
		{
			throw new RuntimeException("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}
		
		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Expression arg = args.getPrimaryElement(i);
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter._symbolTable.setSymbol(param.param.getValue(), val);
		}
		
		// And transfer control to the function
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
		
		// The result was already put on the runtime stack
		
		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			interpreter._symbolTable.removeSymbols(param._name);
		}
	}
}

