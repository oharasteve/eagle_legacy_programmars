// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran;

import com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
import com.eagle.programmar.Fortran.Terminals.Fortran_Number;
import com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
import com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Fortran_Number number;
	public @P(20) Fortran_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Fortran_Expression()
	{
	    super(_operators);
	}

	public Fortran_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Fortran_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("-");
		public @S(20) Fortran_Expression expr;
	}
	
	public static @P(110) class Fortran_FunctionCall extends PrimaryOperator
	{
		public @S(10) Fortran_Identifier_Reference variable;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<Fortran_Expression,PunctuationComma> args;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(120) class Fortran_Subscript extends PrimaryOperator
	{
		public @S(10) Fortran_Identifier_Reference variable;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<Fortran_Expression,PunctuationColon> args;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(130) class Fortran_BuiltIn extends PrimaryOperator
	{
		public @S(10) Fortran_KeywordChoice builtinConstant = new Fortran_KeywordChoice(
				".FALSE.",
				".TRUE.");
	}

	public static @P(140) class Fortran_VariableExpression extends PrimaryOperator
	{
		public @S(10) Fortran_Variable variable;
	}

	public static @P(150) class Fortran_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Fortran_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(160) class Fortran_BracketExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Fortran_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Fortran_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("*", "/");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class Fortran_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("+", "-");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Fortran_StringConcatenation extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_Punctuation operator = new Fortran_Punctuation("//");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class Fortran_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class Fortran_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_EqOper oper;
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Fortran_EqOper extends TokenChooser
		{
			public @CHOICE Fortran_KeywordChoice EQ = new Fortran_KeywordChoice(".EQ.", ".NE.");
			public @CHOICE Fortran_PunctuationChoice oper = new Fortran_PunctuationChoice("=", "/=");
		}
	}
	
	public static @P(550) class Fortran_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_Keyword andOperator = new Fortran_Keyword(".AND.");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Fortran_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Fortran_Keyword orOperator = new Fortran_Keyword(".OR.");
		public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
	}
}