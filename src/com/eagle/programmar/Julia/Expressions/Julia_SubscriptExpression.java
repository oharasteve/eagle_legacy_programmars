// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Julia_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Julia_Expression expr = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Julia_Expression subscr1;
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT Julia_SubscriptionEnd subscr2;
	public @S(60) PunctuationRightBracket rightBracket;

	public static class Julia_SubscriptionEnd extends TokenChooser
	{
		public @CHOICE Julia_Keyword XXEND = new Julia_Keyword("end");
		public @CHOICE Julia_Expression XXsubscr;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int start = interpreter.getIntValue(subscr1) - 1;
		if (subscr2.getWhich() instanceof Julia_Expression)
		{
			int stop = interpreter.getIntValue(subscr2.getWhich()) - 1;
			interpreter.pushStr(str.substring(start, stop));
		}
		else
		{
			interpreter.pushStr(str.substring(start));
		}
	}
}
