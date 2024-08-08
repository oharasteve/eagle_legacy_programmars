// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Ruby_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Ruby_Variable var;
	public @S(20) Ruby_PunctuationChoice equals = new Ruby_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ruby_Expression expr;
	public @S(40) Ruby_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference id = var.vars.first();
		EagleValue val = interpreter.getEagleValue(expr);

		switch (equals.getValue())
		{
		case "=":
			interpreter.setSymbol(var, id.getValue(), val);
			break;
		case "+=":
			EagleValue oldValue = interpreter.findSymbol(id.getValue());
			int old = oldValue.forceIntegerValue();
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(old + val.forceIntegerValue()));
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}
	}
}
