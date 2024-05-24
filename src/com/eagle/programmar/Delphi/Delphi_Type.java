// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Type extends TokenChooser
{
	public @CHOICE Delphi_KeywordChoice base = new Delphi_KeywordChoice("Boolean", "Integer", "LongInt", "Int64",
			"String", "Text");

	public @CHOICE Delphi_Identifier_Reference userType;
	public @CHOICE Delphi_Class classDefinition;

	public @CHOICE static class Delphi_Enum extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Delphi_EnumValue, PunctuationComma> enumValues;
		public @S(30) PunctuationRightParen rightParen;

		public static class Delphi_EnumValue extends TokenSequence
		{
			public @S(10) Delphi_Variable_Definition name;
			public @S(20) PunctuationEquals equals;
			public @S(30) Delphi_Expression value;
		}
	}

	public @CHOICE static class Delphi_Array extends TokenSequence
	{
		public @S(10) Delphi_Keyword ARRAY = new Delphi_Keyword("Array");
		public @S(20) @OPT Delphi_ArraySize size;
		public @S(30) Delphi_Keyword OF = new Delphi_Keyword("Of");
		public @S(40) Delphi_Type type;

		public static class Delphi_ArraySize extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) SeparatedList<Delphi_Expression, PunctuationComma> subscripts;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}

	public @CHOICE static class Delphi_Range extends TokenSequence
	{
		public @S(10) Delphi_Number low;
		public @S(20) Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
		public @S(30) Delphi_Number high;
	}

	public @CHOICE static class Delphi_Type_Record extends TokenSequence
	{
		public @S(10) Delphi_Keyword RECORD = new Delphi_Keyword("Record");
		public @S(20) TokenList<Delphi_RecordEntry> entries;
		public @S(30) Delphi_Keyword END = new Delphi_Keyword("End");

		public static class Delphi_RecordEntry extends TokenSequence
		{
			public @S(10) SeparatedList<Delphi_Variable_Definition, PunctuationComma> vars;
			public @S(20) PunctuationColon colon;
			public @S(30) Delphi_Type type;
			public @S(40) PunctuationSemicolon semicolon;
		}
	}

	public @FIRST static class Delphi_String extends TokenSequence
	{
		public @S(10) Delphi_Keyword STRING = new Delphi_Keyword("String");
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Delphi_Expression expr;
		public @S(40) PunctuationRightBracket rightBracket;
	}
}
