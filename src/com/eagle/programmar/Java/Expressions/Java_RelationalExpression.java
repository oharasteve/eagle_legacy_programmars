// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Relational;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Functions.Java_EqualsMethod;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_Relational<Java_Expression>
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "==":
			interpreter.pushBool(leftInt == rightInt);
			return;
		case "!=":
			interpreter.pushBool(leftInt != rightInt);
			return;
		case "<":
			interpreter.pushBool(leftInt < rightInt);
			return;
		case "<=":
			interpreter.pushBool(leftInt <= rightInt);
			return;
		case ">":
			interpreter.pushBool(leftInt > rightInt);
			return;
		case ">=":
			interpreter.pushBool(leftInt >= rightInt);
			return;
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
	
	private static boolean isString(AbstractExpression expression)
	{
		Java_Expression expr = (Java_Expression) expression;
		if (expr.getWhich() instanceof Java_Literal) return true;
		return false;
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "==":
			return generator.newRelationalExpression(leftExpr,	
					RelationalEnum.EQUALS, rightExpr, this);
		case "!=":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(leftExpr,
					RelationalEnum.GREATER_EQUALS, rightExpr, this);
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}

	@Override
	public Java_Expression generateRelational(Java_Expression leftExpr, RelationalEnum relOp,
			Java_Expression rightExpr, AbstractToken source)
	{
		if (isString(leftExpr) || isString(rightExpr))
		{
			Java_EqualsMethod equals = Java_EqualsMethod.newEqualsMethod(leftExpr, rightExpr);
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
