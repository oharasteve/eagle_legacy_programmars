// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Relational;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class CSharp_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Relational<CSharp_Expression>
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/comparison-operators") CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

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
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
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
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
	
	@Override
	public CSharp_Expression generateRelational(CSharp_Expression leftExpr,
			RelationalEnum relOp, CSharp_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		
		switch (relOp)
		{
		case EQUALS:
			this.operator = new CSharp_PunctuationChoice("==");
			break;
		case NOT_EQUALS:
			this.operator = new CSharp_PunctuationChoice("!=");
			break;
		case LESS_THAN:
			this.operator = new CSharp_PunctuationChoice("<");
			break;
		case LESS_EQUALS:
			this.operator = new CSharp_PunctuationChoice("<=");
			break;
		case GREATER_THAN:
			this.operator = new CSharp_PunctuationChoice(">");
			break;
		case GREATER_EQUALS:
			this.operator = new CSharp_PunctuationChoice(">=");
			break;
		}
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
