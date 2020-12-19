// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Character;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Delphi_Number number;
	public @P(20) Delphi_HexNumber hex;
	public @P(30) Delphi_Literal literal;
	public @P(40) Delphi_Character character;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Delphi_Expression()
	{
	    super(_operators);
	}

	public Delphi_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Delphi_Builtins extends PrimaryOperator
	{
		public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice("False", "True", "Nil");
	}
	
	public static @P(110) class Delphi_Parens extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Delphi_Expression,PunctuationComma> exprList;
		public @S(30) PunctuationRightParen rightParen;		
	}
	
	public static @P(120) class Delphi_Brackets extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Delphi_Expression,PunctuationComma> exprs;
		public @S(30) PunctuationRightBracket rightBracket;		
	}
	
	public static @P(130) class Delphi_Builtin_Function_Call extends PrimaryOperator
	{
		public @S(10) Delphi_KeywordChoice name = new Delphi_KeywordChoice("Odd", "Pred", "Succ");
		public @S(20) Delphi_Parameter_List params;
	}
	
	public static @P(135) class Delphi_Function_Call extends PrimaryOperator
	{
		public @S(10) Delphi_Variable name;
		public @S(20) Delphi_Parameter_List params;
	}
	
	public static @P(140) class Delphi_Cast extends PrimaryOperator
	{
		public @S(10) Delphi_Type type;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Delphi_Expression expr;
		public @S(40) PunctuationRightParen rightParen;		
	}
	
	public static @P(150) class Delphi_VariableExpression extends PrimaryOperator
	{
		public @S(10) Delphi_Variable variable;
	}
	
	public static @P(160) class Delphi_UnarySign extends PrimaryOperator
	{
		public @S(10) Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
		public @S(20) Delphi_Expression expr;
	}
	
	public static @P(170) class Delphi_NotOp extends PrimaryOperator
	{
		public @S(10) Delphi_Keyword NOT = new Delphi_Keyword("Not");
		public @S(20) Delphi_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(180) class Delphi_Dot_Expression extends PrecedenceOperator 
	{
		public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(190) class Delphi_Multiplicative_Expression extends PrecedenceOperator 
	{
		public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Delphi_Multiplicative_Operator multOp;
		public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Multiplicative_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("*", "/");
			public @CHOICE Delphi_KeywordChoice word = new Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
		}
	}

	public static @P(200) class Delphi_Additive_Expression extends PrecedenceOperator 
	{
		public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Delphi_Additive_Operator addOp;
		public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Delphi_Additive_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("+", "-");
			public @CHOICE Delphi_KeywordChoice OR = new Delphi_KeywordChoice("Or", "Xor");
		}
	}
	
	public static @P(210) class Delphi_Relational_Expression extends PrecedenceOperator 
	{
		public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Delphi_Relational_Operator relOp;
		public @S(30) @OPT Delphi_Comment comment;
		public @S(40) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

		public static class Delphi_Relational_Operator extends TokenChooser
		{
			public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("=", "<>", "<", ">", "<=", ">=");
			public @CHOICE Delphi_KeywordChoice IN = new Delphi_KeywordChoice("In", "Is");
		}
	}
	
	public static @P(220) class Delphi_DotDot_Expression extends PrecedenceOperator 
	{
		public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
		public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}
}
