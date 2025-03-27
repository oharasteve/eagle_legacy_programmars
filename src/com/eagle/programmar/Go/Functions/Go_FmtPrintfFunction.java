// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Format;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_FmtPrintfFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Go_Keyword FMT = new Go_Keyword("fmt");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Go_Keyword PRINTF = new Go_Keyword("Printf");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) SeparatedList<Go_Expression, PunctuationComma> arguments;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String formatted = Go_Format.format(interpreter, arguments);
		if (formatted.endsWith("\\n"))
		{
			formatted = formatted.substring(0, formatted.length()-2);
		}
		System.out.println(formatted);
	}
}
