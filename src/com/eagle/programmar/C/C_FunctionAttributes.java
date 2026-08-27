// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_FunctionAttributes extends TokenSequence
{
	public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
	public @S(20) PunctuationLeftParen leftParen1;
	public @S(30) PunctuationLeftParen leftParen2;
	public @S(40) TokenList<C_FunctionAttribute> attributes;
	public @S(50) PunctuationRightParen rightParen1;
	public @S(60) PunctuationRightParen rightParen2;
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static class C_FunctionMessage extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) C_Keyword MESSAGE = new C_Keyword("message");
		public @S(30) PunctuationEquals equals;
		public @S(40) C_Literal message;
	}

	public static class C_FunctionAttribute extends TokenChooser
	{
		public @CHOICE C_KeywordChoice XXATTR = new C_KeywordChoice(
				"__always_inline__",
				"__cold__",
				"__const__",
				"__leaf__",
				"__malloc__",
				"__noreturn__",
				"__nothrow__",
				"__pure__",
				"__warn_unused_result__");

		public @CHOICE static class C_FunctionAttributeAvailability extends TokenSequence
		{
			public @S(10) C_KeywordChoice AVAILABILITY = new C_KeywordChoice("availability", "__availability__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_KeywordChoice MACOS = new C_KeywordChoice(
					"driverkit",
					"ios",
					"macos",
					"macosx",
					"swift",
					"tvos",
					"visionos",
					"watchos");
			public @S(40) PunctuationComma comma;
			public @S(50) C_FunctionAttrAvail avail;
			public @S(60) PunctuationRightParen rightParen;
			
			public static class C_FunctionAttrAvail extends TokenChooser
			{
				public @CHOICE static class C_FunctionAvailIntroduced extends TokenSequence
				{
					public @S(10) C_Keyword INTRODUCED = new C_Keyword("introduced");
					public @S(20) PunctuationEquals equals;
					public @S(30) C_Number number1;		// Includes the period
					public @S(40) @OPT PunctuationPeriod dot;
					public @S(50) @OPT C_Number number2;
				}
				
				public @CHOICE static class C_FunctionUnvailable extends TokenSequence
				{
					public @S(10) C_Keyword UNAVAILABLE = new C_Keyword("unavailable");
					public @S(20) @OPT C_FunctionMessage message;
				}
				
				public @CHOICE static class C_FunctionDeprecated2 extends TokenSequence
				{
					public @S(10) C_Keyword DEPRECATED = new C_Keyword("deprecated");
					public @S(20) PunctuationEquals equals;
					public @S(30) C_Number number;		// Includes the period
					public @S(40) C_FunctionMessage message;
				}
			}
		}

		public @CHOICE static class C_FunctionAttributeFormat extends TokenSequence
		{
			public @S(10) C_Keyword FORMAT = new C_Keyword("__format__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_KeywordChoice FMTS = new C_KeywordChoice(
					"__printf__",
					"__scanf__",
					"__strfmon__");
			public @S(40) PunctuationComma comma1;
			public @S(50) C_Number number1;
			public @S(60) PunctuationComma comma2;
			public @S(70) C_Number number2;
			public @S(80) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeFormatArg extends TokenSequence
		{
			public @S(10) C_Keyword FORMATARG = new C_Keyword("format_arg");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Number numb;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeAligned extends TokenSequence
		{
			public @S(10) C_Keyword ALIGNED = new C_Keyword("aligned");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Number numb;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeDeprecated1 extends TokenSequence
		{
			public @S(10) C_Keyword DEPRECATED = new C_Keyword("__deprecated__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Literal message;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeNonNull extends TokenSequence
		{
			public @S(10) C_Keyword NONNULL = new C_Keyword("__nonnull__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeAllocAlign extends TokenSequence
		{
			public @S(10) C_Keyword ALLOCALIGN = new C_Keyword("alloc_align");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
			public @S(40) PunctuationRightParen rightParen;
		}

		public @CHOICE static class C_FunctionAttributeAllocSize extends TokenSequence
		{
			public @S(10) C_KeywordChoice ALLOCSIZE = new C_KeywordChoice("alloc_size", "__alloc_size__");
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
		
		public @CHOICE static class C_FunctionAttributeSwift extends TokenSequence
		{
			public @S(10) C_Keyword SWIFT = new C_Keyword("__swift_attr__");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) C_Literal unsafe;
			public @S(40) PunctuationRightParen rightParen;
		}
	}
}
