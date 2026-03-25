// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Methods.Java_EqualsMethod;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
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
		_metrics.operated(leftValue.getType(), rightValue.getType());

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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		switch (operator.toString())
		{
		case "==":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.EQUALS, rightExpr, this);
		case "!=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(types, leftExpr,
					RelationalEnum.GREATER_EQUALS, rightExpr, this);
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}

	private static boolean isString(AbstractExpression expression)
	{
		Java_Expression expr = (Java_Expression) expression;
		if (expr.getWhich() instanceof Java_Literal) return true;
		return false;
	}

	public static Java_Expression generateRelational(Oper2Types types, Java_Expression leftExpr, RelationalEnum relOp,
			Java_Expression rightExpr, AbstractToken source)
	{
		Java_RelationalExpression relExp = new Java_RelationalExpression();
		boolean doStrings = false;
		if (types != null)
		{
			if (types._type1 == TypeEnum.STRING && types._type2 == TypeEnum.STRING)
			{
				doStrings = true;
			}
		}

		if (!doStrings)
		{
			if (isString(leftExpr) || isString(rightExpr))
			{
				doStrings = true;
			}
		}

		if (doStrings)
		{
			Java_Expression parenExpr = Java_ParenthesizedExpression.generateParentheses(leftExpr, null);
			Java_EqualsMethod equals = Java_EqualsMethod.newEqualsMethod(parenExpr, rightExpr);
			equals.setTransformationSource(source);
			Java_Expression equalsExpr = Java_Generator.wrapExpression(equals);
			switch (relOp)
			{
			case EQUALS:
				return equalsExpr;
			case NOT_EQUALS:
				return Java_LogicalNotExpression.generateLogicalNot(equalsExpr, source);
			default:
				throw new RuntimeException("Unable to handle " + relOp + " with strings");
			}
		}

		relExp.left = leftExpr;
		relExp.right = rightExpr;
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
		relExp.operator.setValue(oper);
		relExp.setTransformationSource(source);
		return Java_Generator.wrapExpression(relExp);
	}
}
