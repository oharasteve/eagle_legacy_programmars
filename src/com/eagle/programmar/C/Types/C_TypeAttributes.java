// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2024

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_TypeAttributes extends TokenSequence
{
	public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
	public @S(20) PunctuationLeftParen leftParen1;
	public @S(30) PunctuationLeftParen leftParen2;
	public @S(40) C_TypedefAttribute attrib;
	public @S(50) @OPT TokenList<C_TypedefMoreAttributes> more;
	public @S(60) PunctuationRightParen rightParen2;
	public @S(70) PunctuationRightParen rightParen1;

	public static class C_TypedefAttribute extends TokenChooser
	{
		public @CHOICE static class C_TypedefAttributeMode extends TokenSequence
		{
			public @S(10) C_Keyword MODE = new C_Keyword("__mode__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Keyword WORD = new C_Keyword("__word__");
			public @S(40) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class C_TypedefAttributeAligned extends TokenSequence
		{
			public @S(10) C_Keyword ALIGNED = new C_Keyword("__aligned__");
			public @S(20) PunctuationLeftParen leftParen1;
			public @S(30) C_Keyword ALIGNOF = new C_Keyword("__alignof__");
			public @S(40) PunctuationLeftParen leftParen2;
			public @S(50) C_Type type;
			public @S(60) PunctuationRightParen rightParen2;
			public @S(70) PunctuationRightParen rightParen1;
		}
	}

	public static class C_TypedefMoreAttributes extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) C_TypedefAttribute attrib;
	}
}