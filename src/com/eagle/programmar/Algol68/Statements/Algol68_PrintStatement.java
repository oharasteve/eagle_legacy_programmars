// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_PrintStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Algol68_Keyword PRINT = new Algol68_Keyword("PRINT");
	public @S(20) Algol68_Punctuation leftParen1 = new Algol68_Punctuation("(");
	public @S(30) Algol68_Punctuation leftParen2 = new Algol68_Punctuation("(");
	public @S(40) SeparatedList<Algol68_PrintWhat, PunctuationComma> what;
	public @S(50) Algol68_Punctuation rightParen1 = new Algol68_Punctuation(")");
	public @S(60) Algol68_Punctuation rightParen2 = new Algol68_Punctuation(")");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static class Algol68_PrintNewLine extends TokenSequence implements AbstractStatement
	{
		public @S(10) Algol68_Keyword NEW = new Algol68_Keyword("NEW");
		public @S(20) Algol68_Keyword LINE = new Algol68_Keyword("LINE");
	}

	public static class Algol68_PrintWhat extends TokenChooser
	{
		public @CHOICE Algol68_Expression XXexpr;
		public @CHOICE Algol68_PrintNewLine XXnewLine;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (int i = 0; i < what.getPrimaryCount(); i++)
		{
			AbstractToken item = what.getPrimaryElement(i).getWhich();
			if (item instanceof Algol68_Expression)
			{
				String result = interpreter.getStrValue(item);
				System.out.print(result);
			}
			else if (item instanceof Algol68_PrintNewLine)
			{
				System.out.println();
			}
			else
				throw new RuntimeException("Unable to print " + what);
		}
	}
}
