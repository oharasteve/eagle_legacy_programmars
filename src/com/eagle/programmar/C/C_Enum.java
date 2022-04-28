// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class C_Enum extends TokenSequence
{
	public @S(10) C_Keyword ENUM = new C_Keyword("enum");
	public @S(20) @OPT C_Keyword CLASS = new C_Keyword("class");
	public @S(30) @OPT C_Identifier_Reference typeName;
	public @S(40) @OPT C_EnumType enumType;
	public @S(50) @OPT C_TypeEnumValues values;
	
	public static class C_EnumType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) C_Type type;
	}
	
	public static class C_TypeEnumValues extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<C_Comment> comment1;
		public @S(30) C_Variable_Definition firstEnum;
		public @S(40) @OPT C_EnumInitializer init;
		public @S(50) @OPT TokenList<C_Comment> comment2;
		public @S(60) @OPT TokenList<C_MoreEnums> moreEnums;
		public @S(70) PunctuationRightBrace rightBrace;
		
		public static class C_MoreEnums extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT TokenList<C_Comment> comment1;
			public @S(30) C_Variable_Definition nextEnum;
			public @S(40) @OPT C_EnumInitializer init;
			public @S(50) @OPT TokenList<C_Comment> comment2;
		}
		
		public static class C_EnumInitializer extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro1;
			public @S(30) C_Expression initialValue;
		}
	}
}
