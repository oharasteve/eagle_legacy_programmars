// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Subscript;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_FunctionPointerCall extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen1;
	public @S(20) @OPT PunctuationStar star;
	public @S(30) C_MethodParameter firstParam;
	public @S(40) TokenList<C_MethodNextParam> moreParams;
	public @S(50) @OPT C_Subscript subscript;
	public @S(60) PunctuationRightParen rightParen1;
	public @S(70) PunctuationLeftParen leftParen2;
	public @S(80) C_ArgumentList argList;
	public @S(90) PunctuationRightParen rightParen2;
	
	public static class C_MethodParameter extends TokenChooser
	{
		public @CHOICE C_Identifier_Reference XXid;
		
		public @CHOICE static class C_MethodParam_Parens extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen1;
			public @S(20) @OPT PunctuationStar star;
			public @S(30) C_Identifier_Reference id;
			public @S(40) PunctuationRightParen rightParen1;
		}
	}
	
	public static class C_MethodNextParam extends TokenSequence
	{
		public @S(10) C_PunctuationChoice arrow = new C_PunctuationChoice("->", ".");
		public @S(20) C_MethodParameter nextParam;
	}
}
