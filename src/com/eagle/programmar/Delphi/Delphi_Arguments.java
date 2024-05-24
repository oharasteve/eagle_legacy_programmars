// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Arguments extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT Delphi_OneArgument firstArg;
	public @S(30) @OPT TokenList<Delphi_MoreArguments> moreArgs;
	public @S(40) PunctuationRightParen rightParen;

	public static class Delphi_OneArgument extends TokenSequence
	{
		public @S(10) @OPT Delphi_Keyword VAR = new Delphi_Keyword("Var");
		public @S(20) SeparatedList<Delphi_Variable, PunctuationComma> names;
		public @S(30) PunctuationColon colon;
		public @S(40) Delphi_Type type;
		public @S(50) @OPT Delphi_InitialValue initialValue;
	}

	public static class Delphi_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationSemicolon semicolon;
		public @S(20) @OPT Delphi_OneArgument nextArg;
	}

	public static class Delphi_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Delphi_Expression expr;
	}
}
