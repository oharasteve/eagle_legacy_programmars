// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Statements.Ada_FunctionCall.Ada_FunctionArguments;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.programmar.Ada.Terminals.Ada_Literal;
import com.eagle.programmar.Ada.Terminals.Ada_Number;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Ada_Number number;
	public @P(20) Ada_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Ada_Expression()
	{
	    super(_operators);
	}

	public Ada_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Ada_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Ada_Variable methodName;
		public @S(20) @OPT Ada_Punctuation question = new Ada_Punctuation("?");
		public @S(30) Ada_FunctionArguments args;
	}

	public static @P(110) class Ada_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Ada_PunctuationChoice preIncrementOperator = new Ada_PunctuationChoice("++", "--");
		public @S(20) Ada_Variable var;
	}

	public static @P(120) class Ada_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Ada_Variable var;
		public @S(20) Ada_PunctuationChoice postIncrementOperator = new Ada_PunctuationChoice("++", "--");
	}

	public static @P(130) class Ada_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Ada_PunctuationChoice operator = new Ada_PunctuationChoice("-");
		public @S(20) Ada_Expression expr;
	}

	public static @P(140) class Ada_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Ada_Punctuation logicalNotOperator = new Ada_Punctuation('~');
		public @S(20) Ada_Expression expr;
	}
	
	public static @P(150) class Ada_NotExpression extends PrimaryOperator
	{
		public @S(10) Ada_Punctuation notOperator = new Ada_Punctuation('!');
		public @S(20) Ada_Expression expr;
	}
	
	public static @P(160) class Ada_BuiltIn extends PrimaryOperator
	{
		public @S(10) Ada_KeywordChoice builtinConstant = new Ada_KeywordChoice("false", "true");
	}
	
	public static @P(170) class Ada_VariableExpression extends PrimaryOperator
	{
		public @S(10) Ada_Variable variable;
	}
	
	public static @P(180) class Ada_BracketsExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Ada_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static @P(190) class Ada_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Ada_Expression,PunctuationComma> expressions;
		public @S(30) PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(400) class Ada_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression expr = new Ada_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Ada_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Ada_SubscriptionEnd subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
		
		public static class Ada_SubscriptionEnd extends TokenChooser
		{
			public @CHOICE Ada_Keyword END = new Ada_Keyword("end");
			public @CHOICE Ada_Expression subscr2;
		}
	}

	public static @P(410) class Ada_Subfield extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Ada_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_MultOper operator;
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Ada_MultOper extends TokenChooser
		{
			public @CHOICE Ada_PunctuationChoice operator = new Ada_PunctuationChoice("*", "/");
			public @CHOICE Ada_Keyword MOD = new Ada_Keyword("mod");
		}
	}

	public static @P(430) class Ada_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_PunctuationChoice operator = new Ada_PunctuationChoice("+", "-");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Ada_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_PunctuationChoice operator = new Ada_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(450) class Ada_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_PunctuationChoice operator = new Ada_PunctuationChoice("=", "/=");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class Ada_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_Keyword andOperator = new Ada_Keyword("and");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(470) class Ada_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_Keyword orOperator = new Ada_Keyword("or");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(480) class Ada_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression var = new Ada_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Ada_PunctuationChoice equals = new Ada_PunctuationChoice(
				":=",
				"*=",
				"+=",
				"-=");
		public @S(30) Ada_Expression expr;
	}
	
	public static @P(490) class Ada_RangeExpression extends PrecedenceOperator
	{
		public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Ada_Punctuation dotDot = new Ada_Punctuation("..");
		public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	}
	
}
