// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2020

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_Type extends TokenChooser implements AbstractType
{
	public @CHOICE static class Rust_TypePrimitive extends TokenSequence
	{
		public @S(10) @OPT Rust_Punctuation ampersand = new Rust_Punctuation("&");
		public @S(20) @OPT Rust_TypePrimitiveStatic typeStatic;
		public @S(30) Rust_KeywordChoice PRIMITIVE = new Rust_KeywordChoice("bool", "i32", "str", "String", "usize");

		public static class Rust_TypePrimitiveStatic extends TokenSequence
		{
			public @S(10) Rust_Punctuation quote = new Rust_Punctuation("'");
			public @S(20) Rust_Keyword STATIC = new Rust_Keyword("static");
		}
	}

	public @CHOICE static class Rust_TypeArray extends TokenSequence
	{
		public @S(10) Rust_Punctuation ampersand = new Rust_Punctuation("&");
		public @S(20) Rust_TypeArrayStatic typeStatic;
		public @S(30) PunctuationLeftBracket leftBracket;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) Rust_Type subType;
		public @S(60) PunctuationRightParen rightParen;
		public @S(70) PunctuationRightBracket rightBracket;

		public static class Rust_TypeArrayStatic extends TokenSequence
		{
			public @S(10) Rust_Punctuation quote = new Rust_Punctuation("'");
			public @S(20) Rust_Keyword STATIC = new Rust_Keyword("static");
		}
	}
}