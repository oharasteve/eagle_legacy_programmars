// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_RelationCondition extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(40) COBOL_RelationalOperator operator;
	public @S(50) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.canonicalForm(); // Returns "<", "=", etc.
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator.getWhich(), oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		boolean not = NOT.isPresent();
		boolean result;

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = interpreter.getStrValue(left);
			String rightStr = interpreter.getStrValue(right);
			switch (oper)
			{
			case "=":
				result = leftStr.equals(rightStr);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " for strings");
			}
		}
		else
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			switch (oper)
			{
			case "=":
				result = leftInt == rightInt;
				break;
			case "<":
				result = leftInt < rightInt;
				break;
			case "<=":
				result = leftInt <= rightInt;
				break;
			case ">":
				result = leftInt > rightInt;
				break;
			case ">=":
				result = leftInt >= rightInt;
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " for integers");
			}
		}

		if (not) result = !result;
		interpreter.pushBool(result);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		String oper = operator.canonicalForm(); // Returns "<", "=", etc.
		RelationalEnum newOper;
		
		if (NOT.isPresent())
		{
			switch (oper)
			{
			case "=":
				newOper = RelationalEnum.NOT_EQUALS;
				break;
			case "<":
				newOper = RelationalEnum.GREATER_EQUALS;
				break;
			case "<=":
				newOper = RelationalEnum.GREATER_THAN;
				break;
			case ">":
				newOper = RelationalEnum.LESS_EQUALS;
				break;
			case ">=":
				newOper = RelationalEnum.LESS_THAN;
				break;
			default:
				throw new RuntimeException("Unexpected relational operator: NOT " + oper);
			}
		}
		else
		{
			switch (oper)
			{
			case "=":
				newOper = RelationalEnum.EQUALS;
				break;
			case "<":
				newOper = RelationalEnum.LESS_THAN;
				break;
			case "<=":
				newOper = RelationalEnum.LESS_EQUALS;
				break;
			case ">":
				newOper = RelationalEnum.GREATER_THAN;
				break;
			case ">=":
				newOper = RelationalEnum.GREATER_EQUALS;
				break;
			default:
				throw new RuntimeException("Unexpected relational operator: " + oper);
			}
		}
		return generator.newRelationalExpression(types, leftExpr, newOper, rightExpr, this);
	}
}
