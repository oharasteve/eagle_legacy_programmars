// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_PutStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @OPT Ada_Put_Unbounded_IO io;
	public @S(20) Ada_KeywordChoice PUT = new Ada_KeywordChoice("put", "put_line");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT Ada_Expression expr;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) PunctuationSemicolon semicolon;

	public static class Ada_Put_Unbounded_IO extends TokenSequence
	{
		public @S(10) Ada_Keyword UNBOUNDED_IO = new Ada_Keyword("Unbounded_IO");
		public @S(20) PunctuationPeriod dot;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = null;
		if (expr != null && expr.isPresent())
		{
			val = interpreter.getStrValue(expr);
		}
		
		switch (PUT.getValue().toLowerCase())
		{
		case "put":
			if (val != null)
			{
				System.out.print(val);
			}
			return;
		case "put_line":
			if (val != null)
			{
				System.out.println(val);
			}
			else
			{
				System.out.println();
			}
			return;
		}
		throw new RuntimeException("Unexpected PUT command: " + PUT.getValue());
	}
}
