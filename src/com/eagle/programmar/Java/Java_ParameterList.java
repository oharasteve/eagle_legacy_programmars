// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
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
		public @S(20) @NOSPACE Java_Type jtype;
		public @S(30) @OPT Java_Punctuation elipsis = new Java_Punctuation("...");
		public @S(40) Java_Variable_Definition id;
		public @S(50) @OPT TokenList<Java_EmptySubscript> emptySubscripts;
		
		public static class Java_EmptySubscript extends TokenSequence
		{
			public @S(10) Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		}
		
		public static class Java_MethodParameterPrefix extends TokenChooser
		{
			public @CHOICE Java_Keyword FINAL = new Java_Keyword("final");

			public @CHOICE static class Java_MethodNullable extends TokenSequence
			{
				public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
				public @S(20) Java_KeywordChoice NULLABLE = new Java_KeywordChoice("Nullable", "NonNull");
			}

			public @LAST static class Java_MethodQualifier extends TokenSequence
			{
				public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
				public @S(20) Java_Identifier qualifier;
				public @S(30) @OPT SeparatedList<PunctuationPeriod, Java_Identifier> more;
			}

			public @CHOICE static class Java_MethodSuppress extends TokenSequence
			{
				public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
				public @S(20) @NOSPACE Java_Keyword SUPPRESS = new Java_Keyword("SuppressWarnings");
				public @S(30) @NOSPACE PunctuationLeftParen leftParen;
				public @S(40) @NOSPACE Java_Literal warning;
				public @S(50) @NOSPACE PunctuationRightParen rightParen;
			}
		}
	}
		
	public static class Java_MoreParameters extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) Java_MethodParameter param;
	}
}
