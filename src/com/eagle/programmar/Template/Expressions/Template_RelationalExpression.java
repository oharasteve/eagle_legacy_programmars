// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Template.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Terminals.Template_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Template_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Template_PunctuationChoice operator = new Template_PunctuationChoice("==", "!=", "<", ">", "<=",
			">=");
	public @S(30) Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		String oper = operator.toString();
		switch (oper)
		{
		case "<":
			interpreter.pushBool(leftValue < rightValue);
			break;
		case "<=":
			interpreter.pushBool(leftValue <= rightValue);
			break;
		case "==":
			interpreter.pushBool(leftValue == rightValue);
			break;
		case "!=":
			interpreter.pushBool(leftValue != rightValue);
			break;
		case ">=":
			interpreter.pushBool(leftValue >= rightValue);
			break;
		case ">":
			interpreter.pushBool(leftValue > rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected relational operator: " + operator);
		}
	}
}
