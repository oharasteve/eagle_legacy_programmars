// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_LambdaExpression extends PrimaryOperator
{
	public @S(10) Java_LambdaVariables params;
	public @S(20) Java_Punctuation arrow = new Java_Punctuation("->");
	public @S(30) Java_LambdaValue value;

	public static class Java_LambdaVariables extends TokenChooser
	{
		public @CHOICE Java_Identifier XXid;

		public @CHOICE static class Java_LambdaVariableList extends TokenSequence
		{
			public @S(10) PunctuationLeftParen lParen;
			public @S(20) @OPT SeparatedList<Java_Identifier, PunctuationComma> params;
			public @S(30) PunctuationRightParen rParen;
		}

		public @CHOICE static class Java_LambdaTypedVariableList extends TokenSequence
		{
			public @S(10) PunctuationLeftParen lParen;
			public @S(20) @OPT SeparatedList<Java_TypedIdentifier, PunctuationComma> params;
			public @S(30) PunctuationRightParen rParen;

			public static class Java_TypedIdentifier extends TokenSequence
			{
				public @S(10) @OPT Java_Annotation annotation;
				public @S(20) Java_Type type;
				public @S(30) Java_Identifier id;
			}
		}
	}

	public static class Java_LambdaValue extends TokenChooser
	{
		public @CHOICE Java_Expression XXvalue;

		public @FIRST static class Java_LambdaBlock extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<Java_StatementOrComment> statements;
			public @S(30) PunctuationRightBrace rightBrace;
		}
	}
}
