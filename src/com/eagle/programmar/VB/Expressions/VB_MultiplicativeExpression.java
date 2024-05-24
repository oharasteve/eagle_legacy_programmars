// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;

public class VB_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_MultiplyOperation operator;
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	public static class VB_MultiplyOperation extends TokenChooser
	{
		public @CHOICE VB_Keyword MOD = new VB_Keyword("mod");
		public @CHOICE VB_PunctuationChoice op = new VB_PunctuationChoice("*", "/", "\\");
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
			case "mod":
				interpreter.pushInt(leftValue % rightValue);
				return;
			case "\\":
				interpreter.pushInt(leftValue / rightValue);
				return;
			}
		}
		throw new RuntimeException("Unable to handle " + operator + " in VB_MultiplicativeExpression");
	}
}
