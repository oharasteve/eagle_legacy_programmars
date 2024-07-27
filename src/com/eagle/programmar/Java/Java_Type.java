// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_Type extends TokenSequence implements AbstractType
{
	public @S(10) @OPT Java_Comment comment;
	public @S(20) Java_TypeName typeName;
	public @S(30) @OPT @NOSPACE Java_GenericType genericType;
	public @S(40) @OPT @NOSPACE TokenList<Java_ArrayType> arrayTypes;

	public static class Java_ArrayType extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE PunctuationRightBracket rightBracket;
	}

	public static class Java_GenericType extends TokenSequence
	{
		public @S(10) Java_Punctuation lessThan = new Java_Punctuation('<');
		public @S(20) @OPT @NOSPACE Java_Type subType1;
		public @S(30) @OPT Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		public @S(40) @OPT TokenList<Java_MoreTypes> moreType;
		public @S(50) @NOSPACE Java_Punctuation greaterThan = new Java_Punctuation('>');

		public static class Java_MoreTypes extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Java_Type subType2;
		}
	}

	// Delay finding this one until after looking for [] and <>
	public static class Java_TypeName extends TokenChooser
	{
		public @CHOICE Java_KeywordChoice XXprimitive = new Java_KeywordChoice(
				"void", "boolean", "byte", "short", "int",
				"long", "char", "float", "double", "String", "class");

		public @CHOICE static class Java_IdList extends TokenSequence
		{
			public @S(10) Java_Identifier_Reference typeName;
			public @S(20) @OPT Java_ExtendsType extendsType;
			public @S(30) @OPT TokenList<Java_MoreIds> moreIds;
			public @S(40) @OPT Java_ExtendsMultiple multiple;

			public static class Java_MoreIds extends TokenSequence
			{
				public @S(10) @NOSPACE PunctuationPeriod dot;
				public @S(20) @NOSPACE Java_TypeName nextId;
			}

			public static class Java_ExtendsMultiple extends TokenSequence
			{
				public @S(10) Java_Punctuation ampersand = new Java_Punctuation('&');
				public @S(20) Java_Identifier_Reference typeName;
				public @S(30) @OPT Java_ExtendsType extendsType;
				public @S(40) @OPT TokenList<Java_MoreIds> moreIds;
			}
		}

		public @CHOICE static class Java_GenericTypeQuestion extends TokenSequence
		{
			public @S(10) Java_Punctuation question = new Java_Punctuation('?');
			public @S(20) @OPT Java_ExtendsType extendsType;
		}
	}

	public static class Java_ExtendsType extends TokenSequence
	{
		public @S(10) Java_KeywordChoice EXTENDS = new Java_KeywordChoice("extends", "super");
		public @S(20) SeparatedList<Java_Identifier_Reference, PunctuationPeriod> typeName;
	}
}
