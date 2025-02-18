// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.programmar.Rexx.Terminals.Rexx_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;

public class Rexx_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rexx_Expression left = new Rexx_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rexx_MultiplyOperation operator;
	public @S(30) Rexx_Expression right = new Rexx_Expression(this, AllowedPrecedence.HIGHER);

	public static class Rexx_MultiplyOperation extends TokenChooser
	{
		public @CHOICE Rexx_Keyword XXMOD = new Rexx_Keyword("mod");
		public @CHOICE Rexx_PunctuationChoice XXop = new Rexx_PunctuationChoice("*", "/", "%", "//");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = operator.getWhich();
		if (which instanceof TerminalToken)
		{
			String oper = ((TerminalToken) which).getValue();
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			switch (oper)
			{
			case "*":
				interpreter.pushInt(leftValue * rightValue);
				return;
			case "/":
				interpreter.pushDouble((double) leftValue / rightValue);
				return;
			case "//":
				interpreter.pushInt(leftValue % rightValue);
				return;
			case "%":
				interpreter.pushInt(leftValue / rightValue);
				return;
			}
		}
		throw new RuntimeException("Unable to handle " + operator + " in Rexx_MultiplicativeExpression");
	}
}
