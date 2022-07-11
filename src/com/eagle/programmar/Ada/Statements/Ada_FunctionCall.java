// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_FunctionCall extends TokenSequence
{
	public @S(10) Ada_Variable func;
	public @S(20) @OPT Ada_FunctionArguments args;
	public @S(30) PunctuationSemicolon semicolon;
	
	public static class Ada_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Ada_FunctionArg,PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;

		public static class Ada_FunctionArg extends TokenChooser
		{
			public @CHOICE Ada_Expression expr;
			
			public @CHOICE static class Ada_FunctionSetArg extends TokenSequence
			{
				public @S(10) Ada_Identifier_Reference id;
				public @S(20) Ada_Punctuation arrow = new Ada_Punctuation("=>");
				public @S(30) Ada_Expression expr;
			}
		}
	}
}
