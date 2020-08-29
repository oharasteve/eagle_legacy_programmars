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
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class C_Enum extends TokenSequence
{
	public C_Keyword ENUM = new C_Keyword("enum");
	public @OPT C_Identifier_Reference typeName;
	public @OPT C_TypeEnumValues values;
	
	public static class C_TypeEnumValues extends TokenSequence
	{
		public PunctuationLeftBrace leftBrace;
		public @OPT TokenList<C_Comment> comment1;
		public C_Variable_Definition firstEnum;
		public @OPT C_EnumInitializer init;
		public @OPT TokenList<C_Comment> comment2;
		public @OPT TokenList<C_MoreEnums> moreEnums;
		public PunctuationRightBrace rightBrace;
		
		public static class C_MoreEnums extends TokenSequence
		{
			public PunctuationComma comma;
			public @OPT TokenList<C_Comment> comment1;
			public C_Variable_Definition nextEnum;
			public @OPT C_EnumInitializer init;
			public @OPT TokenList<C_Comment> comment2;
		}
		
		public static class C_EnumInitializer extends TokenSequence
		{
			public PunctuationEquals equals;
			public @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro1;
			public C_Expression initialValue;
		}
	}
}