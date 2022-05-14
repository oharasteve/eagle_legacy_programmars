// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Subscript;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CPlus_Expression extends C_Expression
{
	public static @P(500) class CPlus_NewExpression extends PrimaryOperator
	{
		public @S(10) C_Keyword NEW = new C_Keyword("new");
		public @S(20) C_Type type;
		public @S(30) @OPT CPlus_NewWhat what;
		
		public static class CPlus_NewWhat extends TokenChooser
		{
			public @CHOICE C_Subscript size;
			
			public @CHOICE static class CPlus_Parentheses extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) @OPT SeparatedList<C_Expression, PunctuationComma> expression;
				public @S(30) PunctuationRightParen rightParen;
			}
			
		}
	}

//	public static @P(210) class CPlus_NamespaceGlobal extends PrimaryOperator
//	{
//		public @S(10) @OPT C_Punctuation colonColon = new C_Punctuation("::");
//	}
//	
//	public static @P(510) class CPlus_NamespaceSub extends PrecedenceOperator
//	{
//		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
//		public @S(20) @OPT CPlus_Generic generic;
//		public @S(30) C_Punctuation colonColon = new C_Punctuation("::");
//		public @S(40) C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);
//	}
}
