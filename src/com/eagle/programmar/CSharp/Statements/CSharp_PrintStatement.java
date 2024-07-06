// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_PrintStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
	public @S(30) @NEWLINE CSharp_Keyword CONSOLE = new CSharp_Keyword("Console");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE @OPT CSharp_KeywordChoice OUT = new CSharp_KeywordChoice("Error", "Out");
	public @S(60) @NOSPACE @OPT PunctuationPeriod dot3;
	public @S(70) @NOSPACE CSharp_KeywordChoice WRITE = new CSharp_KeywordChoice("Flush", "ReadLine", "SetOut", "Write", "WriteLine");
	public @S(80) @NOSPACE PunctuationLeftParen leftParen;
	public @S(90) @NOSPACE @OPT SeparatedList<CSharp_Expression,PunctuationComma> exprs;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;
	public @S(110) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(exprs.first());
		switch (WRITE.getValue())
		{
		case "Write":
			System.out.print(val);
			return;
		case "WriteLine":
			System.out.println(val);
			return;
		}
		
		throw new RuntimeException("Unexpected keyword: " + WRITE.getValue());
	}
}
