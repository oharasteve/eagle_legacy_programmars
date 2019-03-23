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
		public PunctuationLeftParen leftParen;
		public Powershell_Expression expr;
		public PunctuationRightParen rightParen;		
	}
	
	public static @P(110) class Powershell_Negative extends PrimaryOperator
	{
		public Powershell_Punctuation negative = new Powershell_Punctuation('-');
		public Powershell_Expression expr;
	}
	
	public static @P(120) class Powershell_NotOp extends PrimaryOperator
	{
		public Powershell_Keyword NOT = new Powershell_Keyword("-Not");
		public Powershell_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(130) class Powershell_Multiplicative_Expression extends PrecedenceOperator
	{
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("*", "/");
		public Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(140) class Powershell_Additive_Expression extends PrecedenceOperator
	{
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("+", "-");
		public Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(150) class Powershell_Relational_Expression extends PrecedenceOperator
	{
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_KeywordChoice operator = new Powershell_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-lt", "-le");
		public Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
}
