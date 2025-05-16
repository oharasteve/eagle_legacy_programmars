// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Scala_Assignment extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) Scala_Variable var;
	public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Scala_Expression expr;
	public @S(40) Scala_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Scala_Identifier_Reference id = var.vars.first();
		switch (operator.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, id.getValue(), val);
			break;
		case "+=":
			int newVal1 = interpreter.getIntValue(expr);
			EagleValue oldVar1 = interpreter.findSymbol(id.toString());
			EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
			interpreter.setSymbol(var, id.getValue(), newValue1);
			break;
		case "-=":
			int newVal2 = interpreter.getIntValue(expr);
			EagleValue oldVar2 = interpreter.findSymbol(id.toString());
			EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
			interpreter.setSymbol(var, id.getValue(), newValue2);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}
	}
}
