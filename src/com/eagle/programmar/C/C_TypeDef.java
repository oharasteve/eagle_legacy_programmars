// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_TypeDef extends TokenSequence
{
	public @S(10) @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @S(20) C_Keyword TYPEDEF = new C_Keyword("typedef");
	public @S(30) @OPT C_Keyword INTERFACE = new C_Keyword("interface");
	public @S(40) C_TypeDef_What what;
	public @S(50) @OPT TokenList<C_TypedefAttributes> attributes;
	public @S(60) PunctuationSemicolon semicolon;

	public static class C_TypeDef_What extends TokenChooser
	{
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment XXmacro;

		public @CHOICE static class C_TypeDef_Data extends TokenSequence
		{
			public @S(10) C_Type type;
			public @S(20) @OPT TokenList<PunctuationStar> stars;
			public @S(30) C_Type_Definition typeName;
			public @S(40) @OPT C_Subscript subscript;
			public @S(50) @OPT TokenList<C_TypeDefMore> more;

			public static class C_TypeDefMore extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) @OPT TokenList<PunctuationStar> stars;
				public @S(30) C_Type_Definition typeName;
				public @S(40) @OPT C_Subscript subscript;
			}
		}

		public @LAST static class C_TypeDef_Function extends TokenSequence
		{
			public @S(10) C_Type returnType;
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT TokenList<PunctuationStar> stars;
			public @S(40) C_Function_Definition funcName;
			public @S(50) PunctuationRightParen rightParen;
			public @S(60) C_Function_ParameterDefs params;
		}

		// Just like C_TypeDef_Function but no parens ... I didn't know this was valid
		// syntax!
		public @FIRST static class C_TypeDef_NoParensFunction extends TokenSequence
		{
			public @S(10) C_Type returnType;
			public @S(20) C_Function_Definition funcName;
			public @S(30) C_Function_ParameterDefs params;
		}
	}

	public static class C_TypedefAttributes extends TokenSequence
	{
		public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PunctuationLeftParen leftParen2;
		public @S(40) C_TypedefAttribute attrib;
		public @S(50) @OPT TokenList<C_TypedefMoreAttributes> more;
		public @S(60) PunctuationRightParen righttParen1;
		public @S(70) PunctuationRightParen righttParen2;

		public static class C_TypedefAttribute extends TokenChooser
		{
			public @CHOICE static class C_TypedefAttributeMode extends TokenSequence
			{
				public @S(10) C_Keyword MODE = new C_Keyword("__mode__");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) C_Keyword WORD = new C_Keyword("__word__");
				public @S(40) PunctuationRightParen righttParen;
			}
		}

		public static class C_TypedefMoreAttributes extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) C_TypedefAttribute attrib;
		}
	}
}
