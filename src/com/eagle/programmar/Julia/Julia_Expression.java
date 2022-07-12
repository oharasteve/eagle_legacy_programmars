// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia;

import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.programmar.Julia.Terminals.Julia_KeywordChoice;
import com.eagle.programmar.Julia.Terminals.Julia_Literal;
import com.eagle.programmar.Julia.Terminals.Julia_Number;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Julia_Number number;
	public @P(20) Julia_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Julia_Expression()
	{
	    super(_operators);
	}

	public Julia_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Julia_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Julia_Variable methodName;
		public @S(20) @OPT Julia_Punctuation question = new Julia_Punctuation("?");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT SeparatedList<Julia_Expression,PunctuationComma> argList;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static @P(110) class Julia_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Julia_PunctuationChoice preIncrementOperator = new Julia_PunctuationChoice("++", "--");
		public @S(20) Julia_Variable var;
	}

	public static @P(120) class Julia_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Julia_Variable var;
		public @S(20) Julia_PunctuationChoice postIncrementOperator = new Julia_PunctuationChoice("++", "--");
	}

	public static @P(130) class Julia_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("-");
		public @S(20) Julia_Expression expr;
	}

	public static @P(140) class Julia_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Julia_Punctuation logicalNotOperator = new Julia_Punctuation('~');
		public @S(20) Julia_Expression expr;
	}
	
	public static @P(150) class Julia_NotExpression extends PrimaryOperator
	{
		public @S(10) Julia_Punctuation notOperator = new Julia_Punctuation('!');
		public @S(20) Julia_Expression expr;
	}
	
	public static @P(160) class Julia_BuiltIn extends PrimaryOperator
	{
		public @S(10) Julia_KeywordChoice builtinConstant = new Julia_KeywordChoice("false", "true");
	}
	
	public static @P(170) class Julia_VariableExpression extends PrimaryOperator
	{
		public @S(10) Julia_Variable variable;
	}
	
	public static @P(180) class Julia_BracketsExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Julia_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static @P(190) class Julia_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Julia_Expression expression;
		public @S(40) PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(400) class Julia_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression expr = new Julia_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Julia_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Julia_SubscriptionEnd subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
		
		public static class Julia_SubscriptionEnd extends TokenChooser
		{
			public @CHOICE Julia_Keyword END = new Julia_Keyword("end");
			public @CHOICE Julia_Expression subscr2;
		}
	}

	public static @P(410) class Julia_Subfield extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Julia_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("*", "/", "%");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(430) class Julia_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("+", "-");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Julia_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(450) class Julia_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("==", "!=");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class Julia_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_Punctuation andOperator = new Julia_Punctuation("&&");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(470) class Julia_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Julia_Punctuation orOperator = new Julia_Punctuation("||");
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(480) class Julia_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression var = new Julia_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Julia_PunctuationChoice equals = new Julia_PunctuationChoice(
				":=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Julia_Expression expr;
	}
	
	public static @P(490) class Julia_RangeExpression extends PrecedenceOperator
	{
		public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationColon colon;
		public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) @OPT Julia_Range_Increment increment;
		
		public static class Julia_Range_Increment extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) Julia_Expression expr;
		}
	}
}
