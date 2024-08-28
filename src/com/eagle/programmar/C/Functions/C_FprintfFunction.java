// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Format;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_FprintfFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Keyword FPRINTF = new C_Keyword("fprintf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_KeywordChoice STDOUT = new C_KeywordChoice("stdout", "stderr");
	public @S(40) PunctuationComma comma;
	public @S(50) SeparatedList<C_Expression, PunctuationComma> args;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String formatted = C_Format.format(interpreter, args);
		switch (STDOUT.toString())
		{
		case "stdout":
			System.out.println(formatted);
			return;
		case "stderr":
			System.err.println(formatted);
			return;
		}
	}
}
