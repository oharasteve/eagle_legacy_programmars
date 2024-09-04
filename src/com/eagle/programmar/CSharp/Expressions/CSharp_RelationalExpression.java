// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class CSharp_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

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
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (operator.toString())
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
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
	
	public static CSharp_RelationalExpression generateExpression(AbstractExpression leftExpr, RelationalEnum relOp,
			AbstractExpression rightExpr, AbstractToken source)
	{
		CSharp_RelationalExpression expr = new CSharp_RelationalExpression();
		expr.left = (CSharp_Expression) leftExpr;
		expr.right = (CSharp_Expression) rightExpr;
		
		switch (relOp)
		{
		case EQUALS:
			expr.operator = new CSharp_PunctuationChoice("==");
			break;
		case NOT_EQUALS:
			expr.operator = new CSharp_PunctuationChoice("!=");
			break;
		case LESS_THAN:
			expr.operator = new CSharp_PunctuationChoice("<");
			break;
		case LESS_EQUALS:
			expr.operator = new CSharp_PunctuationChoice("<=");
			break;
		case GREATER_THAN:
			expr.operator = new CSharp_PunctuationChoice(">");
			break;
		case GREATER_EQUALS:
			expr.operator = new CSharp_PunctuationChoice(">=");
			break;
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
