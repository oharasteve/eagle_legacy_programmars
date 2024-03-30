// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
import com.eagle.programmar.Template.Terminals.Template_Literal;
import com.eagle.programmar.Template.Terminals.Template_Number;
import com.eagle.programmar.Template.Terminals.Template_Punctuation;
import com.eagle.programmar.Template.Terminals.Template_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Template_Expression extends PrecedenceChooser implements AbstractExpression
{
	protected static OperatorList _operators = new OperatorList();

	public Template_Expression()
	{
	    super(_operators);
	}

	public Template_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Template_Number number;
	public @P(20) Template_Literal literal;
	public @P(30) Template_Identifier_Reference id;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Template_Parens extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Template_Expression expr;
		public @S(30) PunctuationRightParen rightParen;		
	}
	
	public static @P(110) class Template_Negative extends PrimaryOperator
	{
		public @S(10) Template_Punctuation negative = new Template_Punctuation('-');
		public @S(20) Template_Expression expr;
	}
	
	public static @P(120) class Template_NotOp extends PrimaryOperator
	{
		public @S(10) Template_Punctuation NOT = new Template_Punctuation('!');
		public @S(20) Template_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(130) class Template_Multiplicative_Expression extends PrecedenceOperator
	{
		public @S(10) Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Template_PunctuationChoice operator = new Template_PunctuationChoice("*", "/");
		public @S(30) Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(140) class Template_Additive_Expression extends PrecedenceOperator
	{
		public @S(10) Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Template_PunctuationChoice operator = new Template_PunctuationChoice("+", "-");
		public @S(30) Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(150) class Template_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Template_PunctuationChoice operator = new Template_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public @S(30) Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);
	}
}
