// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_PutStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT Ada_Put_Unbounded_IO io;
	public @S(20) Ada_KeywordChoice PUT = new Ada_KeywordChoice("put", "put_line");
	public @S(30) Ada_Expression expr;
	public @S(40) PunctuationSemicolon semicolon;
	
	public static class Ada_Put_Unbounded_IO extends TokenSequence
	{
		public @S(10) Ada_Keyword UNBOUNDED_IO = new Ada_Keyword("Unbounded_IO");
		public @S(20) PunctuationPeriod dot;
	}
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expr);
		System.out.println(result.toString());
	}
}
