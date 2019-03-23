// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 25, 2018

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeUserDefined.C_TypeStar;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CPlus_ArgumentList extends TokenSequence
{
	public CPlus_ExpressionArg arg;
	public @OPT C_Comment comment;
	public @OPT TokenList<CPlus_MoreArguments> moreArgs;
	public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class CPlus_ExpressionArg extends TokenChooser
	{
		public @FIRST CPlus_Expression expr;
		public @CHOICE C_Keyword CONST = new C_Keyword("const");
		public @CHOICE C_TypePrimitive primitiveType;
		
		public @CHOICE static class CPlus_ExpressionArgType extends TokenSequence
		{
			public C_Identifier_Reference typeRef;
			public TokenList<C_TypeStar> stars;
		}
	}
	
	public static class CPlus_MoreArguments extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT C_Comment comment1;
		public CPlus_ExpressionArg arg;
		public @OPT C_Comment comment2;
	}
}
