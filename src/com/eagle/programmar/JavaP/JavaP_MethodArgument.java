// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class JavaP_MethodArgument extends TokenChooser
{
	public @CHOICE JavaP_Punctuation question = new JavaP_Punctuation('?');

	public @CHOICE static class JavaP_MethodArg extends TokenSequence
	{
		public @S(10) @OPT JavaP_QuestionExtends question;
		public @S(20) @OPT JavaP_TypeExtends type;
		public @S(30) JavaP_QualifiedName name;
		public @S(40) @OPT JavaP_OneClassGeneric generic;
		public @S(50) @OPT JavaP_EmptySubscript subscript;

		public static class JavaP_QuestionExtends extends TokenSequence
		{
			public @S(10) JavaP_Punctuation question = new JavaP_Punctuation('?');
			public @S(20) JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
		}

		public static class JavaP_TypeExtends extends TokenSequence
		{
			public @S(10) JavaP_Identifier typeName;
			public @S(20) JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
		}

		public static class JavaP_EmptySubscript extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) PunctuationRightBracket rightBracket;
		}
	}
}