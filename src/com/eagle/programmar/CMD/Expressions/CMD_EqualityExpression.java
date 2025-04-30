// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Format;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class CMD_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CMD_Expression left = new CMD_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMD_IfOperator operator;
	public @S(30) CMD_Expression right = new CMD_Expression(this, AllowedPrecedence.HIGHER);

	public static class CMD_IfOperator extends TokenChooser
	{
		public @CHOICE CMD_KeywordChoice XXoperator = new CMD_KeywordChoice("equ", "neq");
		public @CHOICE CMD_Punctuation XXequals = new CMD_Punctuation("==");
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
			_metrics = new Operator2Metrics(interpreter._metrics, this, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		String leftStr = leftValue.forceStringValue();
		String leftVal = CMD_Format.format(interpreter, leftStr);
		String rightStr = rightValue.forceStringValue();
		String rightVal = CMD_Format.format(interpreter, rightStr);
		switch (oper.toLowerCase())
		{
		case "equ", "==":
			interpreter.pushBool(leftVal.equalsIgnoreCase(rightVal));
			return;
		case "neq":
			interpreter.pushBool(!leftVal.equalsIgnoreCase(rightVal));
			return;
		default:
			throw new RuntimeException("Cannot handle equality operator: " + oper);
		}
	}
}
