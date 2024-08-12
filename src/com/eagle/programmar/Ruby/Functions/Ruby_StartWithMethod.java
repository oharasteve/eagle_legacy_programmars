// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ruby_StartWithMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ruby_Keyword STARTWITH = new Ruby_Keyword("start_with");
	public @S(40) Ruby_Punctuation question = new Ruby_Punctuation("?");
	public @S(50) PunctuationLeftParen leftParen;
	public @S(60) Ruby_Expression patternExpr;
	public @S(70) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		String pattern = interpreter.getStrValue(patternExpr);
		interpreter.pushBool(str.startsWith(pattern));
	}
}
