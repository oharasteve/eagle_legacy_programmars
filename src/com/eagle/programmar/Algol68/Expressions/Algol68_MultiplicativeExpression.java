// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Algol68_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Algol68_MultOper operator;
	public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Algol68_MultOper extends TokenChooser
	{
		public @CHOICE Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("*", "/", "%");
		public @CHOICE Algol68_KeywordChoice MOD = new Algol68_KeywordChoice("mod", "over");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.getWhich().toString())
		{
		case "*" :
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/", "over" :
			if (leftValue % rightValue == 0)
			{
				interpreter.pushInt(leftValue / rightValue);
				return;
			}
			interpreter.pushDouble(leftValue / (double) rightValue);
			return;
		case "%", "mod" :
			interpreter.pushInt(leftValue % rightValue);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + operator);
	}
}