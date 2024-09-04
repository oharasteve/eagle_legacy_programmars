// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class Java_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "==":
			interpreter.pushBool(leftValue == rightValue);
			return;
		case "!=":
			interpreter.pushBool(leftValue != rightValue);
			return;
		case "<":
			interpreter.pushBool(leftValue < rightValue);
			return;
		case "<=":
			interpreter.pushBool(leftValue <= rightValue);
			return;
		case ">":
			interpreter.pushBool(leftValue > rightValue);
			return;
		case ">=":
			interpreter.pushBool(leftValue >= rightValue);
			return;
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
	
	public static Java_RelationalExpression generateExpression(AbstractExpression leftExpr, RelationalEnum relOp,
			AbstractExpression rightExpr, AbstractToken source)
	{
		Java_RelationalExpression expr = new Java_RelationalExpression();
		expr.left = (Java_Expression) leftExpr;
		expr.right = (Java_Expression) rightExpr;
		
		switch (relOp)
		{
		case EQUALS:
			expr.operator = new Java_PunctuationChoice("==");
			break;
		case NOT_EQUALS:
			expr.operator = new Java_PunctuationChoice("!=");
			break;
		case LESS_THAN:
			expr.operator = new Java_PunctuationChoice("<");
			break;
		case LESS_EQUALS:
			expr.operator = new Java_PunctuationChoice("<=");
			break;
		case GREATER_THAN:
			expr.operator = new Java_PunctuationChoice(">");
			break;
		case GREATER_EQUALS:
			expr.operator = new Java_PunctuationChoice(">=");
			break;
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
