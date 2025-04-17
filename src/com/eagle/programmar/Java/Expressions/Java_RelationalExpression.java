// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Functions.Java_EqualsMethod;
import com.eagle.programmar.Java.Terminals.Java_Literal;
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
	
	private static boolean isString(AbstractExpression expression)
	{
		Java_Expression expr = (Java_Expression) expression;
		if (expr.getWhich() instanceof Java_Literal) return true;
		return false;
	}
	
	public static Java_Expression generateRelational(AbstractExpression leftExpr, RelationalEnum relOp,
			AbstractExpression rightExpr, AbstractToken source)
	{
		if (isString(leftExpr) || isString(rightExpr))
		{
			Java_EqualsMethod equals = Java_EqualsMethod.newEqualsMethod((Java_Expression) leftExpr, (Java_Expression) rightExpr);
			equals.setTransformationSource(source);
			Java_Expression equalsExpr = Java_Generator.wrapExpression(equals);
			switch (relOp)
			{
			case EQUALS:
				return equalsExpr;
			case NOT_EQUALS:
				Java_LogicalNotExpression not = Java_LogicalNotExpression.newNotExpression(equalsExpr);
				not.setTransformationSource(source);
				return Java_Generator.wrapExpression(not);
			default:
				throw new RuntimeException("Unable to handle " + relOp + " with strings");
			}
		}

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
		return Java_Generator.wrapExpression(expr);
	}
}
