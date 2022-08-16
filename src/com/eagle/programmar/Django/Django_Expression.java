// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.Django.Terminals.Django_Number;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.programmar.Django.Terminals.Django_PunctuationChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Django_Number number;
	public @P(20) Django_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Django_Expression()
	{
	    super(_operators);
	}

	public Django_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public @P(100) static class Django_ExpressionRange extends PrimaryOperator
	{
		public @S(10) Django_Keyword RANGE = new Django_Keyword("range");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) HTML_Number number;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public @P(110) static class Django_NotExpression extends PrimaryOperator
	{
		public @S(10) Django_Keyword NOT = new Django_Keyword("not");
		public @S(20) Django_Expression expr;
	}

	public @P(120) static class Django_DefinedExpression extends PrimaryOperator
	{
		public @S(10) Django_Variable variable;
		public @S(20) Django_Keyword IS = new Django_Keyword("is");
		public @S(30) Django_Keyword DEFINED = new Django_Keyword("defined");
	}

	public @P(130) static class Django_BracketsExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT SeparatedList<Django_Expression,PunctuationComma> exprs;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public @P(140) static class Django_ParensExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Django_Expression expr;
		public @S(30) PunctuationRightParen rightParen;
	}

	public @P(150) static class Django_FunctionExpression extends PrimaryOperator
	{
		public @S(10) Django_Variable var;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT SeparatedList<Django_Expression,PunctuationComma> exprs;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public @P(160) static class Django_VariableExpression extends PrimaryOperator
	{
		public @S(10) Django_Variable variable;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) static class Django_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression expr = new Django_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Django_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}
	
	public @P(510) static class Django_BarExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_Punctuation bar = new Django_Punctuation("|");
		public @S(30) Django_BarWhat what;
		
		public static class Django_BarWhat extends TokenChooser
		{
			public @CHOICE Django_KeywordChoice LENGTH = new Django_KeywordChoice(
					"int",
					"length");
			
			public @CHOICE static class Django_BarDefault extends TokenSequence
			{
				public @S(10) Django_Keyword DEFAULT = new Django_Keyword("default");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) Django_Expression expr;
				public @S(40) PunctuationRightParen rightParen;
			}
		}
	}

	public @P(520) static class Django_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_PunctuationChoice operator = new Django_PunctuationChoice(
				"==", "!=", "<=", ">=", "<", ">");
		public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}

	public @P(530) static class Django_FormatExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_Punctuation percent = new Django_Punctuation("%");
		public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}

	public @P(540) static class Django_InExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_Keyword IN = new Django_Keyword("in");
		public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public @P(550) static class Django_AndExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_Keyword OR = new Django_Keyword("or");
		public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}

	public @P(560) static class Django_OrExpression extends PrecedenceOperator
	{
		public @S(10) Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Django_Keyword AND = new Django_Keyword("and");
		public @S(30) Django_Expression right = new Django_Expression(this, AllowedPrecedence.HIGHER);
	}
}
