// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
import com.eagle.programmar.Scala.Terminals.Scala_Literal;
import com.eagle.programmar.Scala.Terminals.Scala_Number;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Scala_Expression()
	{
	    super(_operators);
	}

	public Scala_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Scala_Number number;
	public @P(20) Scala_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Scala_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Scala_Variable methodName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT SeparatedList<Scala_Expression,PunctuationComma> argList;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(110) class Scala_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Scala_PunctuationChoice preIncrementOperator = new Scala_PunctuationChoice("++", "--");
		public @S(20) Scala_Variable var;
	}

	public static @P(120) class Scala_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Scala_Variable var;
		public @S(20) Scala_PunctuationChoice postIncrementOperator = new Scala_PunctuationChoice("++", "--");
	}

	public static @P(130) class Scala_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("-");
		public @S(20) Scala_Expression expr;
	}

	public static @P(140) class Scala_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Scala_Punctuation logicalNotOperator = new Scala_Punctuation('~');
		public @S(20) Scala_Expression expr;
	}
	
	public static @P(150) class Scala_NotExpression extends PrimaryOperator
	{
		public @S(10) Scala_Punctuation notOperator = new Scala_Punctuation('!');
		public @S(20) Scala_Expression expr;
	}
	
	public static @P(160) class Scala_BuiltIn extends PrimaryOperator
	{
		public @S(10) Scala_KeywordChoice builtinConstant = new Scala_KeywordChoice("false", "true");
	}
	
	public static @P(170) class Scala_VariableExpression extends PrimaryOperator
	{
		public @S(10) Scala_Variable variable;
	}
	
	public static @P(180) class Scala_BracesExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) SeparatedList<Scala_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBrace rightBrace;
	}

	public static @P(190) class Scala_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Scala_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Scala_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression expr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Scala_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Scala_Expression subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
	}

	public static @P(510) class Scala_Subfield extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Scala_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("*", "/", "%");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class Scala_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("+", "-");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class Scala_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(550) class Scala_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("==", "!=");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Scala_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_Punctuation andOperator = new Scala_Punctuation("&&");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(570) class Scala_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_Punctuation orOperator = new Scala_Punctuation("||");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(580) class Scala_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression var = new Scala_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Scala_PunctuationChoice equals = new Scala_PunctuationChoice(
				":=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Scala_Expression expr;
	}
	
	public static @P(590) class Scala_RangeExpression extends PrecedenceOperator
	{
		public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Scala_Keyword TO = new Scala_Keyword("to");
		public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	}
}
