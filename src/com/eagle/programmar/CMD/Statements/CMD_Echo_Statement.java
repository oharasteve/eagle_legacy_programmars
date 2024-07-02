// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CMD_Echo_Statement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("echo.mspx") CMD_Keyword ECHO = new CMD_Keyword("echo");
	public @S(20) @OPT PunctuationPeriod dot;
	public @S(30) CMD_RestOfLine line;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = interpreter.getStrValue(line);
		System.out.println(result);
	}
}
