// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_FunctionAttributes extends TokenSequence
{
	public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
	public @S(20) PunctuationLeftParen leftParen1;
	public @S(30) PunctuationLeftParen leftParen2;
	public @S(40) C_FunctionAttribute attrib;
	public @S(50) @OPT TokenList<C_FunctionMoreAttributes> more;
	public @S(60) PunctuationRightParen rightParen1;
	public @S(70) PunctuationRightParen rightParen2;

	public static class C_FunctionAttribute extends TokenChooser
	{
		public @CHOICE C_KeywordChoice XXATTR = new C_KeywordChoice("deprecated", "__const__", "__deprecated__",
				"__leaf__",
				"__malloc__", "__noreturn__", "__nothrow__", "__pure__", "__warn_unused_result__");

		public @CHOICE static class C_FunctionAttributeFormat extends TokenSequence
		{
			public @S(10) C_Keyword FORMAT = new C_Keyword("__format__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_KeywordChoice FMTS = new C_KeywordChoice("__printf__", "__scanf__", "__strfmon__");
			public @S(40) PunctuationComma comma1;
			public @S(50) C_Number number1;
			public @S(60) PunctuationComma comma2;
			public @S(70) C_Number number2;
			public @S(80) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeNonNull extends TokenSequence
		{
			public @S(10) C_Keyword NONNULL = new C_Keyword("__nonnull__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeAllocSize extends TokenSequence
		{
			public @S(10) C_Keyword ALLOCSIZE = new C_Keyword("__alloc_size__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeSentinel extends TokenSequence
		{
			public @S(10) C_Keyword sentinel = new C_Keyword("__sentinel__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Number number;
			public @S(40) PunctuationRightParen rightParen;
		}
	}

	public static class C_FunctionMoreAttributes extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) C_FunctionAttribute attrib;
	}
}