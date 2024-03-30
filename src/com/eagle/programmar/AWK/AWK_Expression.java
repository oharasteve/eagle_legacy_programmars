// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Identifier;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.programmar.AWK.Terminals.AWK_Number;
import com.eagle.programmar.AWK.Terminals.AWK_Pattern;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();
	
	public AWK_Expression()
	{
		super(_operators);
	}
	
	public AWK_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{ 
		super(_operators, allowed, token.getClass());
	}
	
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) AWK_Number number;

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class AWK_GetLine extends PrimaryOperator
	{
		public @S(10) @DOC("#index-getline-command") AWK_Keyword GETLINE = new AWK_Keyword("GETLINE");
		public @S(20) AWK_Variable var;
		public @S(30) AWK_Punctuation lessThan = new AWK_Punctuation('<');
		public @S(40) AWK_Variable file;
	}

	public static @P(110) class AWK_BuiltinFunctionCall extends PrimaryOperator
	{
		public @S(10) AWK_KeywordChoice function = new AWK_KeywordChoice(
				"index",
				"int",
				"length",
				"match",
				"sprintf",
				"strftime",
				"substr"
		);
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT AWK_ArgumentList argList;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public static @P(120) class AWK_UserFunctionCall extends PrimaryOperator
	{
		public @S(10) AWK_Identifier functionName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT AWK_ArgumentList argList;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(130) class AWK_PatternExpression extends PrimaryOperator
	{
		public @S(10) AWK_Pattern pattern;
	}

	public static @P(140) class AWK_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) AWK_Punctuation operator = new AWK_Punctuation("++");
		public @S(20) AWK_Expression expr;
	}

	public static @P(150) class AWK_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) AWK_Punctuation operator = new AWK_Punctuation("--");
		public @S(20) AWK_Expression expr;
	}
	
	public static @P(160) class AWK_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public @S(20) AWK_Punctuation operator = new AWK_Punctuation("++");
	}

	public static @P(170) class AWK_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) AWK_Variable var;		// Cannot be just AWK_Expression -- infinite loop
		public @S(20) AWK_Punctuation operator = new AWK_Punctuation("--");
	}

	public static @P(180) class AWK_Strings extends PrimaryOperator
	{
		public @S(10) TokenList<AWK_StringPiece> pieces;
		
		public static class AWK_StringPiece extends TokenChooser
		{
			public @CHOICE AWK_Literal literal;
			public @CHOICE AWK_BuiltinFunctionCall fn;
			public @CHOICE AWK_UserFunctionCall userfn;

			public @LAST AWK_Identifier_Reference id;
		}
	}
	
	public static @P(190) class AWK_NotExpression extends PrimaryOperator
	{
		public @S(10) AWK_Punctuation operator = new AWK_Punctuation('!');
		public @S(20) AWK_Expression expr;
	}

	public static @P(200) class AWK_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) AWK_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(210) class AWK_DollarParensExpression extends PrimaryOperator
	{
		public @S(10) AWK_Punctuation dollar = new AWK_Punctuation('$');
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) AWK_Expression expression;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class AWK_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) AWK_Expression subscr = new AWK_Expression();
		public @S(40) PunctuationRightBracket rightBracket;
	}
	
	public static @P(510) class AWK_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("*", "/", "%");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class AWK_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("+", "-");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class AWK_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class AWK_RegularExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("~", "!~");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(550) class AWK_InExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_Keyword IN = new AWK_Keyword("in");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class AWK_AndExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_Punctuation andOperator = new AWK_Punctuation("&&");
		public @S(30) @OPT AWK_EndOfLine eoln;		// Hack -- really should switch to Multiline_Syntax
		public @S(40) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(570) class AWK_OrExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_Punctuation orOperator = new AWK_Punctuation("||");
		public @S(30) @OPT AWK_EndOfLine eoln;		// Hack -- really should switch to Multiline_Syntax
		public @S(40) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(580) class AWK_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) AWK_Punctuation questionMark = new AWK_Punctuation('?');
		public @S(30) AWK_Expression middle = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(40) PunctuationColon colon;
		public @S(50) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	}

	public static @P(590) class AWK_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
		public @S(30) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	}
}
