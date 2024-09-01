// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class VB_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_EqualityOperator equalityOperator;
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	public static class VB_EqualityOperator extends TokenChooser
	{
		public @CHOICE VB_PunctuationChoice XXequals = new VB_PunctuationChoice("=");
		public @CHOICE VB_KeywordChoice XXIS = new VB_KeywordChoice("is", "like", "isnot");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (equalityOperator.getWhich().toString())
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (equalityOperator.getWhich().toString())
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			}
		}
		
		throw new RuntimeException("Unexpected equality operator: " + equalityOperator.getWhich());
	}
}
