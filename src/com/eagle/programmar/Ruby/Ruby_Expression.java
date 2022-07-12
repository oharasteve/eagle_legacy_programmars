// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby;

import com.eagle.programmar.Ruby.Terminals.Ruby_KeywordChoice;
import com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
import com.eagle.programmar.Ruby.Terminals.Ruby_Number;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ruby_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Ruby_Number number;
	public @P(20) Ruby_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Ruby_Expression()
	{
	    super(_operators);
	}

	public Ruby_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Ruby_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Ruby_Variable methodName;
		public @S(20) @OPT Ruby_Punctuation question = new Ruby_Punctuation("?");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT SeparatedList<Ruby_Expression,PunctuationComma> argList;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static @P(110) class Ruby_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Ruby_PunctuationChoice preIncrementOperator = new Ruby_PunctuationChoice("++", "--");
		public @S(20) Ruby_Variable var;
	}

	public static @P(120) class Ruby_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Ruby_Variable var;
		public @S(20) Ruby_PunctuationChoice postIncrementOperator = new Ruby_PunctuationChoice("++", "--");
	}

	public static @P(130) class Ruby_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("-");
		public @S(20) Ruby_Expression expr;
	}

	public static @P(140) class Ruby_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Ruby_Punctuation logicalNotOperator = new Ruby_Punctuation('~');
		public @S(20) Ruby_Expression expr;
	}
	
	public static @P(150) class Ruby_NotExpression extends PrimaryOperator
	{
		public @S(10) Ruby_Punctuation notOperator = new Ruby_Punctuation('!');
		public @S(20) Ruby_Expression expr;
	}
	
	public static @P(160) class Ruby_BuiltIn extends PrimaryOperator
	{
		public @S(10) Ruby_KeywordChoice builtinConstant = new Ruby_KeywordChoice("false", "true");
	}
	
	public static @P(170) class Ruby_VariableExpression extends PrimaryOperator
	{
		public @S(10) Ruby_Variable variable;
	}
	
	public static @P(180) class Ruby_BracketsExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Ruby_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static @P(190) class Ruby_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Ruby_Expression expression;
		public @S(40) PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(400) class Ruby_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Ruby_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Ruby_Expression subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
	}

	public static @P(410) class Ruby_Subfield extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Ruby_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("*", "/", "%");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(430) class Ruby_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("+", "-");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Ruby_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(450) class Ruby_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_PunctuationChoice operator = new Ruby_PunctuationChoice("==", "!=");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class Ruby_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_Punctuation andOperator = new Ruby_Punctuation("&&");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(470) class Ruby_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_Punctuation orOperator = new Ruby_Punctuation("||");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(480) class Ruby_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression var = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Ruby_PunctuationChoice equals = new Ruby_PunctuationChoice(
				":=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Ruby_Expression expr;
	}
	
	public static @P(490) class Ruby_RangeExpression extends PrecedenceOperator
	{
		public @S(10) Ruby_Expression left = new Ruby_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ruby_Punctuation dotDot = new Ruby_Punctuation("..");
		public @S(30) Ruby_Expression right = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	}
}
