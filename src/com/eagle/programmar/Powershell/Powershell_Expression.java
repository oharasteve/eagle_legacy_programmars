// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Number;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Expression extends PrecedenceChooser
{
	protected static OperatorList _operators = new OperatorList();

	public @P(10) Powershell_Number number;
	public @P(20) Powershell_Literal literal;
	public @P(30) Powershell_Identifier_Reference id;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Powershell_Expression()
	{
	    super(_operators);
	}

	public Powershell_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Powershell_Parens extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Powershell_Expression expr;
		public @S(30) PunctuationRightParen rightParen;		
	}
	
	public static @P(110) class Powershell_Negative extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation negative = new Powershell_Punctuation('-');
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(120) class Powershell_NotOp extends PrimaryOperator
	{
		public @S(10) Powershell_Keyword NOT = new Powershell_Keyword("-Not");
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(130) class Powershell_Lists extends PrimaryOperator
	{
		public @S(19) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Powershell_Expression expr;
		public @S(40) TokenList<Powershell_ListItem> listItem;
		public @S(50) PunctuationRightParen rightParen;
		
		public static class Powershell_ListItem extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Powershell_Expression expr;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(300) class Powershell_Multiplicative_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("*", "/");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(310) class Powershell_Additive_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("+", "-");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(320) class Powershell_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-lt", "-le");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
}
