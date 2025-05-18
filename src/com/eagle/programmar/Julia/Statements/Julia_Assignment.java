// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Julia_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @OPT Julia_Keyword GLOBAL = new Julia_Keyword("global");
	public @S(20) Julia_Variable var;
	public @S(30) Julia_PunctuationChoice equals = new Julia_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(40) Julia_Expression expr;
	public @S(50) Julia_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Julia_Identifier_Reference id = var.vars.first();
		switch (equals.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, id.getValue(), val);
			break;
		case "+=":
			int newVal = interpreter.getIntValue(expr);
			EagleValue oldVar = interpreter.findSymbol(id.getValue());
			EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
			interpreter.setSymbol(var, id.getValue(), newValue);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}
	}
}
