// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Eaglish_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_MultiplicationOperator operator;
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	public static class Eaglish_MultiplicationOperator extends TokenChooser
	{
		public @CHOICE Eaglish_PunctuationChoice operSymbol = new Eaglish_PunctuationChoice("*");
		public @CHOICE Eaglish_KeywordChoice operWord = new Eaglish_KeywordChoice("DIVIDE_TRUNCATE", "REMAINDER");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		AbstractToken which = operator.getWhich();
		if (which instanceof Eaglish_PunctuationChoice)
		{
			Eaglish_PunctuationChoice oper = (Eaglish_PunctuationChoice) which;
			switch (oper.getValue())
			{
			case "*":
				interpreter.pushInt(leftValue * rightValue);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_MultiplicativeExpression");	
			}
		}
		else if (which instanceof Eaglish_KeywordChoice)
		{
			Eaglish_KeywordChoice oper = (Eaglish_KeywordChoice) which;
			switch (oper.getValue())
			{
			case "DIVIDE_TRUNCATE":
				interpreter.pushInt(leftValue / rightValue);
				break;
			case "REMAINDER":
				interpreter.pushInt(leftValue % rightValue);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_MultiplicativeExpression");	
			}
		}
		else throw new RuntimeException("Unexpected operator: " + which.getClass().getName());
	}
}
