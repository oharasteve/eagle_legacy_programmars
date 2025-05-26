// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Fortran_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Fortran_EqOper operator;
	public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);

	public static class Fortran_EqOper extends TokenChooser
	{
		public @CHOICE Fortran_KeywordChoice XXEQ = new Fortran_KeywordChoice(".EQ.", ".NE.");
		public @CHOICE Fortran_PunctuationChoice XXoper = new Fortran_PunctuationChoice("==", "/=");
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
			switch (oper.toUpperCase())
			{
			case ".EQ.", "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case ".NE.", "/=":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.toUpperCase())
			{
			case ".EQ.", "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case ".NE.", "/=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected equality operator: " + oper);
	}
}
