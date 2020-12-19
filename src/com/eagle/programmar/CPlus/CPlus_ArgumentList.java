// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 25, 2018

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CPlus_ArgumentList extends TokenSequence
{
	public @S(10) CPlus_ExpressionArg arg;
	public @S(20) @OPT C_Comment comment;
	public @S(30) @OPT TokenList<CPlus_MoreArguments> moreArgs;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class CPlus_ExpressionArg extends TokenChooser
	{
		public @FIRST CPlus_Expression expr;
		public @CHOICE C_Keyword CONST = new C_Keyword("const");
		public @CHOICE C_TypePrimitive primitiveType;
		
		public @CHOICE static class CPlus_ExpressionArgType extends TokenSequence
		{
			public @S(10) C_Identifier_Reference typeRef;
			public @S(20) TokenList<C_TypeStar> stars;
		}
	}
	
	public static class CPlus_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT C_Comment comment1;
		public @S(30) CPlus_ExpressionArg arg;
		public @S(40) @OPT C_Comment comment2;
	}
}
