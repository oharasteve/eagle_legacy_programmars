// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class TCL_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_RelOperator operator;
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

	public static class TCL_RelOperator extends TokenChooser
	{
		public @CHOICE TCL_KeywordChoice XXEQ = new TCL_KeywordChoice(
				"lt", "le", "eq", "ne", "gt", "ge");
		public @CHOICE TCL_PunctuationChoice XXoperator = new TCL_PunctuationChoice(
				"<", ">", "<=", ">=", "==", "<>", "!=");
	}

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.getWhich().toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper.toLowerCase())
			{
			case "==", "eq":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=", "ne":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.toLowerCase())
			{
			case "==", "eq":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "<>", "!=", "ne":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<", "lt":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=", "le":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">", "gt":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=", "ge":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
}
