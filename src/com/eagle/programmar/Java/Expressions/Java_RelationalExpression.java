// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Relational;
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

public class Java_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Relational<Java_Expression>
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
	
	@Override
	public Java_Expression generateRelational(Java_Expression leftExpr, RelationalEnum relOp,
			Java_Expression rightExpr, AbstractToken source)
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
				Java_LogicalNotExpression notExpr = new Java_LogicalNotExpression();
				Java_Expression not = notExpr.generateLogicalNot(rightExpr, source);
				return Java_Generator.wrapExpression(not);
			default:
				throw new RuntimeException("Unable to handle " + relOp + " with strings");
			}
		}

		this.left = leftExpr;
		this.right = rightExpr;
		String oper;
		switch (relOp)
		{
		case EQUALS:
			oper = "==";
			break;
		case NOT_EQUALS:
			oper = "!=";
			break;
		case LESS_THAN:
			oper = "<";
			break;
		case LESS_EQUALS:
			oper = "<=";
			break;
		case GREATER_THAN:
			oper = ">";
			break;
		case GREATER_EQUALS:
			oper = ">=";
			break;
		default:
			throw new RuntimeException("Unable to handle operator " + relOp);
		}
		this.operator.setValue(oper);
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
