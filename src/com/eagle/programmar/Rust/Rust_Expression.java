// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_BinaryNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Rust_BinaryNumber bin;
	public @P(20) Rust_HexNumber hex;
	public @P(40) Rust_Number number;
	public @P(50) Rust_Literal literal;
	public @P(60) Rust_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Rust_Expression()
	{
	    super(_operators);
	}

	public Rust_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(200) class Rust_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Java_Variable methodName;
		public @S(20) @OPT Rust_Punctuation bang = new Rust_Punctuation("!");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT SeparatedList<Rust_Expression,PunctuationComma> argList;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static @P(210) class Rust_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Rust_Punctuation neg = new Rust_Punctuation("-");
		public @S(20) Rust_Expression expr;
	}

	public static @P(220) class Rust_NotExpression extends PrimaryOperator
	{
		public @S(10) Rust_Punctuation notOperator = new Rust_Punctuation('!');
		public @S(20) Rust_Expression expr;
	}
	
	public static @P(230) class Rust_BuiltIn extends PrimaryOperator
	{
		public @S(10) Rust_KeywordChoice builtinConstant = new Rust_KeywordChoice("false", "true");
	}
	
	public static @P(240) class Rust_VariableExpression extends PrimaryOperator
	{
		public @S(10) Rust_Variable variable;
	}
	
	public static @P(250) class Rust_RangeExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Rust_Expression expression1;
		public @S(30) Rust_Punctuation dots = new Rust_Punctuation("..");
		public @S(40) Rust_Expression expression2;
		public @S(50) PunctuationRightParen rightParen;
	}
	
	public static @P(260) class Rust_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Rust_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static @P(270) class Rust_ExpressionArray extends PrimaryOperator
	{
		public @S(10) PunctuationAmpersand ampersand;
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) SeparatedList<Rust_Expression,PunctuationComma> exprs;
		public @S(40) PunctuationRightBracket rightBracket;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(300) class Rust_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Rust_Expression subscr1;
		public @S(40) @OPT Rust_Punctuation dots = new Rust_Punctuation("..");
		public @S(50) @OPT Rust_Expression subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
	}

	public static @P(310) class Rust_Subfield extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(320) class Rust_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("*", "/", "%");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(330) class Rust_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("+", "-");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(340) class Rust_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice(">>>", "<<", ">>");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(350) class Rust_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(360) class Rust_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("==", "!=");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(400) class Rust_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation andOperator = new Rust_Punctuation("&&");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(410) class Rust_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation orOperator = new Rust_Punctuation("||");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Rust_DotDotExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation dotDotOperator = new Rust_Punctuation("..");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
}
