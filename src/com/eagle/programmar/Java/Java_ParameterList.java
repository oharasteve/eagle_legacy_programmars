// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ParameterList extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT @NOSPACE Java_MethodParameter param;
	public @S(30) @OPT @NOSPACE TokenList<Java_MoreParameters> moreParams;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	
	public static class Java_MethodParameter extends TokenSequence
	{
		public @S(10) @OPT TokenList<Java_MethodParameterPrefix> prefixes;
		public @S(40) @NOSPACE Java_Type jtype;
		public @S(50) @OPT Java_Punctuation elipsis = new Java_Punctuation("...");
		public @S(60) Java_Variable_Definition id;
		public @S(70) @OPT TokenList<Java_EmptySubscript> emptySubscripts;
		
		public static class Java_EmptySubscript extends TokenSequence
		{
			public @S(10) Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		}
		
		public static class Java_MethodParameterPrefix extends TokenChooser
		{
			public @CHOICE Java_KeywordChoice FINAL = new Java_KeywordChoice("final", "static");
			public @CHOICE Java_Annotation annotation;
		}
	}
		
	public static class Java_MoreParameters extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) Java_MethodParameter param;
	}
}
