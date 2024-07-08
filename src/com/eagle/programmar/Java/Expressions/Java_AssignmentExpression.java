// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Java_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=",
			">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) Java_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof Java_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		Java_VariableExpression varExpr = (Java_VariableExpression) var.getWhich();
		AbstractToken token = varExpr.variable.firstId.getWhich();
		if (token instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) token;
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						id.getValue(), val);
				break;
			case "+=":
				int newVal = interpreter.getIntValue(expr);
				EagleValue oldVar = interpreter._symbolTable.findSymbol(id.toString());
				EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						id.getValue(), newValue);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}
}
