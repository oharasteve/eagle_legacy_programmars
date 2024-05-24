// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.C;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Types.C_TypePrimitive;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class C_ArgumentList extends TokenSequence
{
	public @S(10) @OPT TokenList<C_IgnoreItem> comment1;
	public @S(20) C_ExpressionArg arg;
	public @S(30) @OPT TokenList<C_MoreArguments> moreArgs;
	public @S(40) @OPT TokenList<C_IgnoreItem> comment2;
	public @S(50) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	public static class C_IgnoreItem extends TokenChooser
	{
		public @CHOICE C_Comment comment;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
	}

	public static class C_ExpressionArg extends TokenChooser
	{
		public @FIRST C_Expression expr;
		public @CHOICE C_Keyword CONST = new C_Keyword("const");
		public @CHOICE C_TypePrimitive primitiveType;
		public @LAST C_Lambda lambda;

		public @CHOICE static class C_ExpressionArgType extends TokenSequence
		{
			public @S(10) C_Identifier_Reference typeRef;
			public @S(20) TokenList<C_TypeStar> stars;
		}
	}

	public static class C_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<C_IgnoreItem> comment1;
		public @S(30) C_ExpressionArg arg;
	}
}
