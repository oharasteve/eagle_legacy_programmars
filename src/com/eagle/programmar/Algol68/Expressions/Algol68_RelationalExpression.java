// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Algol68_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Algol68_RelOp relOp;
	public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

	public static class Algol68_RelOp extends TokenChooser
	{
		public @CHOICE Algol68_PunctuationChoice XXsymbol = new Algol68_PunctuationChoice(
				"<", ">", "<=", ">=", "=", "~=", "/=");
		public @CHOICE Algol68_KeywordChoice XXword = new Algol68_KeywordChoice(
				"LT", "LE", "EQ", "NE", "GE", "GT");
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
			switch (relOp.getWhich().toString())
			{
			case "=", "EQ":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "~=", "/=", "NE":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (relOp.getWhich().toString())
			{
			case "=", "EQ":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "~=", "/=", "NE":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<", "LT":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=", "LE":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">", "GT":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=", "GE":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected relational operator: " + relOp.getWhich());
	}
}
