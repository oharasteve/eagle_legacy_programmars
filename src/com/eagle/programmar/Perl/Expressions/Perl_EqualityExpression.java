// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Perl_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_EqualityOperator equalityOperator;
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

	public static class Perl_EqualityOperator extends TokenChooser
	{
		public @CHOICE Perl_KeywordChoice XXEQ = new Perl_KeywordChoice("eq", "ne");
		public @CHOICE Perl_PunctuationChoice XXoperator = new Perl_PunctuationChoice("===", "!==", "==", "!=");
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
			case "==", "===", "eq":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=", "!==", "ne":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (equalityOperator.getWhich().toString())
			{
			case "==", "===", "eq":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=", "!==", "ne":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}
		
		throw new RuntimeException("Unexpected relational operator: " + equalityOperator.getWhich());
	}
}
