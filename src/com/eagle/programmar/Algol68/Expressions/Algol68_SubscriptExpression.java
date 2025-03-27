// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Algol68_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Algol68_Expression expr = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Algol68_Expression subscr1;
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT Algol68_SubscriptionEnd subscr2;
	public @S(60) PunctuationRightBracket rightBracket;

	public static class Algol68_SubscriptionEnd extends TokenChooser
	{
		public @CHOICE Algol68_Keyword XXEND = new Algol68_Keyword("end");
		public @CHOICE Algol68_Expression XXsubscr;
	}
}
