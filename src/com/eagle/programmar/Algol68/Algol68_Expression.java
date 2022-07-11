// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Statements.Algol68_FunctionCall.Algol68_FunctionArguments;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_Literal;
import com.eagle.programmar.Algol68.Terminals.Algol68_Number;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
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

public class Algol68_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Algol68_Number number;
	public @P(20) Algol68_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Algol68_Expression()
	{
	    super(_operators);
	}

	public Algol68_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Algol68_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Algol68_Variable methodName;
		public @S(20) @OPT Algol68_Punctuation question = new Algol68_Punctuation("?");
		public @S(30) Algol68_FunctionArguments args;
	}

	public static @P(110) class Algol68_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Algol68_PunctuationChoice preIncrementOperator = new Algol68_PunctuationChoice("++", "--");
		public @S(20) @NOSPACE Algol68_Variable var;
	}

	public static @P(120) class Algol68_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Algol68_Variable var;
		public @S(20) @NOSPACE Algol68_PunctuationChoice postIncrementOperator = new Algol68_PunctuationChoice("++", "--");
	}

	public static @P(130) class Algol68_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("-");
		public @S(20) Algol68_Expression expr;
	}

	public static @P(140) class Algol68_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Algol68_Punctuation logicalNotOperator = new Algol68_Punctuation('~');
		public @S(20) Algol68_Expression expr;
	}
	
	public static @P(150) class Algol68_BuiltIn extends PrimaryOperator
	{
		public @S(10) Algol68_KeywordChoice builtinConstant = new Algol68_KeywordChoice("FALSE", "TRUE");
	}
	
	public static @P(160) class Algol68_VariableExpression extends PrimaryOperator
	{
		public @S(10) Algol68_Variable variable;
	}
	
	public static @P(170) class Algol68_BracketsExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE SeparatedList<Algol68_Expression,PunctuationComma> expression;
		public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
	}

	public static @P(180) class Algol68_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Algol68_Expression,PunctuationComma> expressions;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static @P(190) class Algol68_ArrayInfo extends PrimaryOperator
	{
		public @S(10) Algol68_KeywordChoice UPB = new Algol68_KeywordChoice("UPB", "LWB");
		public @S(20) Algol68_Variable arrayName;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(400) class Algol68_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression expr = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Algol68_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Algol68_SubscriptionEnd subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
		
		public static class Algol68_SubscriptionEnd extends TokenChooser
		{
			public @CHOICE Algol68_Keyword END = new Algol68_Keyword("end");
			public @CHOICE Algol68_Expression subscr2;
		}
	}

	public static @P(410) class Algol68_Subfield extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Algol68_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_MultOper operator;
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Algol68_MultOper extends TokenChooser
		{
			public @CHOICE Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("*");
			public @CHOICE Algol68_KeywordChoice MOD = new Algol68_KeywordChoice("mod", "over");
		}
	}

	public static @P(430) class Algol68_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("+", "-");
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Algol68_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_RelOp relOp;
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

		public static class Algol68_RelOp extends TokenChooser
		{
			public @CHOICE Algol68_PunctuationChoice symbol = new Algol68_PunctuationChoice("<", ">", "<=", ">=", "=", "~=");
			public @CHOICE Algol68_KeywordChoice word = new Algol68_KeywordChoice("NE");
		}
	}

	public static @P(450) class Algol68_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_Keyword andOperator = new Algol68_Keyword("and");
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class Algol68_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_Keyword orOperator = new Algol68_Keyword("or");
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(470) class Algol68_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression var = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Algol68_PunctuationChoice equals = new Algol68_PunctuationChoice(
				":=",
				"*=",
				"+=",
				"-=");
		public @S(30) Algol68_Expression expr;
	}
	
	public static @P(480) class Algol68_RangeExpression extends PrecedenceOperator
	{
		public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Algol68_Punctuation dotDot = new Algol68_Punctuation("..");
		public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	}
	
}
