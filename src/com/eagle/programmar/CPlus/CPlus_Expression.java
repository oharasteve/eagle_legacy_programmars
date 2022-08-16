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
	// Caution -- cannot conflict with any P() numbers in C_Expression
	public static @P(499) class CPlus_NewExpression extends PrimaryOperator
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
}
