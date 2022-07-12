// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.programmar.FSharp.Terminals.FSharp_Literal;
import com.eagle.programmar.FSharp.Terminals.FSharp_Number;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class FSharp_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) FSharp_Number number;
	public @P(20) FSharp_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public FSharp_Expression()
	{
	    super(_operators);
	}

	public FSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class FSharp_BracketBars extends PrimaryOperator
	{
		public @S(10) FSharp_Punctuation leftBracketBar = new FSharp_Punctuation("[|");
		public @S(20) @OPT FSharp_EndOfLine eoln;
		public @S(30) @OPT @SYNTAX(FSharp_Multiline_Syntax.class)
				SeparatedList<FSharp_Expression,PunctuationSemicolon> vals;
		public @S(40) FSharp_Punctuation rightBarBracket = new FSharp_Punctuation("|]");
	}
	
	public static @P(110) class FSharp_Parens extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) FSharp_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static @P(120) class FSharp_FunctionCall extends PrimaryOperator
	{
		public @S(10) FSharp_Variable functionName;
		public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public @S(30) @NOSPACE @OPT SeparatedList<FSharp_Expression,PunctuationComma> argList;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
	}

	public static @P(130) class FSharp_UnarySign extends PrimaryOperator
	{
		public @S(10) FSharp_PunctuationChoice sign = new FSharp_PunctuationChoice("-");
		public @S(20) FSharp_Expression expr;
	}

	public static @P(140) class FSharp_BuiltIn extends PrimaryOperator
	{
		public @S(10) FSharp_KeywordChoice builtins = new FSharp_KeywordChoice("False", "True");
	}
	
	public static @P(150) class FSharp_VariableExpression extends PrimaryOperator
	{
		public @S(10) FSharp_Variable variable;
	}

	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(400) class FSharp_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) FSharp_Expression expr = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_Punctuation leftDotBracket = new FSharp_Punctuation(".[");
		public @S(30) FSharp_RangeExpr subscr;
		public @S(40) PunctuationRightBracket rightBracket;
		
		public static class FSharp_RangeExpr extends TokenChooser
		{
			public @FIRST static class FSharp_RangeExpr_low_high extends TokenSequence
			{
				public @S(10) FSharp_Expression low;
				public @S(20) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
				public @S(30) @OPT FSharp_Expression high;
			}
			
			public @CHOICE static class FSharp_RangeExpr_low extends TokenSequence
			{
				public @S(10) FSharp_Expression low;
				public @S(20) @OPT FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
			}
			
			public @CHOICE static class FSharp_RangeExpr_high extends TokenSequence
			{
				public @S(10) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
				public @S(20) @OPT FSharp_Expression high;
			}
		}
	}
	
	public static @P(410) class FSharp_Subfield extends PrecedenceOperator
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class FSharp_Multiplicative_Expression extends PrecedenceOperator 
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_PunctuationChoice operator = new FSharp_PunctuationChoice("*", "/", "%");
		public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(430) class FSharp_Additive_Expression extends PrecedenceOperator 
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_PunctuationChoice operator = new FSharp_PunctuationChoice("+", "-");
		public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(440) class FSharp_Relational_Expression extends PrecedenceOperator 
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_PunctuationChoice operator = new FSharp_PunctuationChoice(
				"==", "<>", "<=", ">=", "<", ">");
		public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(450) class FSharp_And_Expression extends PrecedenceOperator 
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_Punctuation AND = new FSharp_Punctuation("&&");
		public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(460) class FSharp_Or_Expression extends PrecedenceOperator 
	{
		public @S(10) FSharp_Expression left = new FSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) FSharp_Punctuation OR = new FSharp_Punctuation("||");
		public @S(30) FSharp_Expression right = new FSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
}
