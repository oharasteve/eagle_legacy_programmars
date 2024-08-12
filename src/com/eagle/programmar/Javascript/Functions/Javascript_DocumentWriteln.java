// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_DocumentWriteln extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Keyword DOCUMENT = new Javascript_Keyword("document");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword WRITELN = new Javascript_Keyword("writeln");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression expr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		if (val.startsWith("<br>")) val = val.substring(4);	// Toss leading <br> if present
		System.out.println(val);
	}
}
