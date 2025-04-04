// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_RelationalExpression extends PrecedenceOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_PunctuationChoice operator = new VB_PunctuationChoice("=", "<=", ">=", "<>", "<", ">");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (operator.toString())
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "<>":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDbl = leftValue.forceDoubleValue();
			double rightDbl = rightValue.forceDoubleValue();
			switch (operator.toString())
			{
			case "=":
				interpreter.pushBool(leftDbl == rightDbl);
				return;
			case "<>":
				interpreter.pushBool(leftDbl != rightDbl);
				return;
			case "<":
				interpreter.pushBool(leftDbl < rightDbl);
				return;
			case "<=":
				interpreter.pushBool(leftDbl <= rightDbl);
				return;
			case ">":
				interpreter.pushBool(leftDbl > rightDbl);
				return;
			case ">=":
				interpreter.pushBool(leftDbl >= rightDbl);
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (operator.toString())
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "<>":
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
		}
		
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "=":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "<>":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
