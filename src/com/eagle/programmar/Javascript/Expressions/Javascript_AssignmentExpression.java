// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof Javascript_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		Javascript_VariableExpression varExpr = (Javascript_VariableExpression) var.getWhich();
		AbstractToken token = varExpr.variable.firstId.getWhich();
		if (token instanceof Javascript_Identifier_Reference)
		{
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) token;
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				int newVal = interpreter.getIntValue(expr);
				EagleValue oldVar = interpreter.findSymbol(id.toString());
				EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
				interpreter.setSymbol(var, id.getValue(), newValue);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}
}
