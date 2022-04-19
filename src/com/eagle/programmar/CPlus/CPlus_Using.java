// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 24, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_Using extends TokenSequence
{
	public @S(10) C_Keyword USING = new C_Keyword("using");
	public @S(20) @OPT C_Keyword NAMESPACE = new C_Keyword("namespace");
	public @S(30) CPlus_UsingWhat what;
	public @S(40) PunctuationSemicolon semicolon;
	
	public static class CPlus_UsingWhat extends TokenChooser
	{
		public @CHOICE C_Expression what;
		public @CHOICE CPlus_UsingColons colons;
	}
	
	public static class CPlus_UsingColons extends TokenSequence
	{
		public @S(10) @OPT C_Punctuation colonColon = new C_Punctuation("::");
		public @S(20) @OPT TokenList<CPlus_UsingColon> idColons;
		public @S(30) C_Identifier_Reference id;

		public static class CPlus_UsingColon extends TokenSequence
		{
			public @S(10) C_Identifier_Reference id;
			public @S(20) C_Punctuation colonColon = new C_Punctuation("::");
		}
	}
}
