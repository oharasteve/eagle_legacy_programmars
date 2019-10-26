// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ParameterList extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @OPT @NOSPACE Java_MethodParameter param;
	public @OPT @NOSPACE TokenList<Java_MoreParameters> moreParams;
	public @NOSPACE PunctuationRightParen rightParen;
	
	public static class Java_MethodParameter extends TokenSequence
	{
		public @OPT TokenList<Java_MethodParameterPrefix> prefixes;
		public @NOSPACE Java_Type jtype;
		public @OPT Java_Punctuation elipsis = new Java_Punctuation("...");
		public Java_Variable_Definition id;
		public @OPT TokenList<Java_EmptySubscript> emptySubscripts;
		
		public static class Java_EmptySubscript extends TokenSequence
		{
			public Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		}
		
		public static class Java_MethodParameterPrefix extends TokenChooser
		{
			public @CHOICE Java_Keyword FINAL = new Java_Keyword("final");

			public @CHOICE static class Java_MethodNullable extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public Java_Keyword NULLABLE = new Java_Keyword("Nullable");
			}

			public @CHOICE static class Java_MethodSuppress extends TokenSequence
			{
				public Java_Punctuation atSign = new Java_Punctuation('@');
				public @NOSPACE Java_Keyword SUPPRESS = new Java_Keyword("SuppressWarnings");
				public @NOSPACE PunctuationLeftParen leftParen;
				public @NOSPACE Java_Literal warning;
				public @NOSPACE PunctuationRightParen rightParen;
			}
		}
	}
		
	public static class Java_MoreParameters extends TokenSequence
	{
		public @NOSPACE PunctuationComma comma;
		public Java_MethodParameter param;
	}
}
