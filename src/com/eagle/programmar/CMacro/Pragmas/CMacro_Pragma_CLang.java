// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_CLang extends TokenSequence
{
	public @S(10) CMacro_KeywordChoice CLANG = new CMacro_KeywordChoice("clang", "GCC");
	public @S(20) CMacro_KeywordChoice DIAGNOSTIC = new CMacro_KeywordChoice("diagnostic", "optimize");
	public @S(30) CMacro_Pragma_CLang_What what;

	public static class CMacro_Pragma_CLang_What extends TokenChooser
	{
		public @CHOICE CMacro_Literal XXliteral;

		public @CHOICE CMacro_KeywordChoice XXPUSH = new CMacro_KeywordChoice("push", "pop");

		public @CHOICE static class CMacro_Pragma_CLangOptimize extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) CMacro_Literal literal; // Such as "fp-contract=off"
			public @S(30) PunctuationRightParen rightParen;
		}

		public @CHOICE static class CMacro_Pragma_CLangIgnored extends TokenSequence
		{
			public @S(10) CMacro_Keyword IGNORED = new CMacro_Keyword("ignored");
			public @S(20) CMacro_Literal warning; // e.g., "-Wunguarded-availability"
		}
	}
}