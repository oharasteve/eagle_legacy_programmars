// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_FunctionCall extends TokenSequence
{
	public @S(10) Algol68_Variable func;
	public @S(20) @OPT Algol68_FunctionArguments args;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	
	public static class Algol68_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Algol68_FunctionArg,PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;

		public static class Algol68_FunctionArg extends TokenChooser
		{
			public @CHOICE Algol68_Expression expr;
			
			public @CHOICE static class Algol68_FunctionSetArg extends TokenSequence
			{
				public @S(10) Algol68_Identifier_Reference id;
				public @S(20) Algol68_Punctuation arrow = new Algol68_Punctuation("=>");
				public @S(30) Algol68_Expression expr;
			}
		}
	}
}
