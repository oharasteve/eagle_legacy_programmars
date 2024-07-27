// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.C_Data.C_FunctionPointer;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Subscript;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_TypeStruct extends TokenSequence
{
	public @S(10) @OPT TokenList<C_Comment> comments;
	public @S(20) C_Keyword STRUCT = new C_Keyword("struct");
	public @S(30) @OPT C_Comment comment1;
	public @S(40) @OPT C_Type_Definition def;
	public @S(50) @OPT C_Comment comment2;
	public @S(60) @OPT C_StructBody body;
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static class C_StructBody extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT C_Comment comment3;
		public @S(30) @OPT TokenList<C_FieldOrComment> fields;
		public @S(40) PunctuationRightBrace rightBrace;
	}

	public static class C_FieldOrComment extends TokenChooser
	{
		public @CHOICE C_Comment XXcomment;
		public @CHOICE C_FunctionPointer XXfunctionPtr;
		public @LAST C_TypeUnion XXunion;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment XXmacro;

		public @CHOICE static class C_Field extends TokenSequence
		{
			public @S(10) C_Type jtype;
			public @S(20) C_Field_Definition id;
			public @S(30) @OPT C_TypeNumberOfBits bits;
			public @S(40) @OPT TokenList<C_Subscript> subscripts;
			public @S(50) @OPT C_FieldInitialValue initialValue;
			public @S(60) @OPT TokenList<C_MoreFields> more;
			public @S(70) PunctuationSemicolon semicolon;
			public @S(80) @OPT TokenList<C_Comment> comments;

			public static class C_TypeNumberOfBits extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) C_Number bits;
			}

			public static class C_MoreFields extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) @OPT TokenList<C_Comment> comments;
				public @S(30) @OPT TokenList<PunctuationStar> stars;
				public @S(40) C_Field_Definition id;
				public @S(50) @OPT TokenList<C_Subscript> subscripts;
				public @S(60) @OPT C_FieldInitialValue initialValue;
			}

			public static class C_FieldInitialValue extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) C_Expression expression;
			}
		}
	}
}