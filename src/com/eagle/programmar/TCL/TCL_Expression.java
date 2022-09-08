// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Symbols.TCL_Function_Reference;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TCL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) TCL_Number number;
	public @P(20) TCL_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public TCL_Expression()
	{
	    super(_operators);
	}

	public TCL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class TCL_VariableExpression extends PrimaryOperator
	{
		public @S(10) @OPT TCL_Punctuation dollar = new TCL_Punctuation("$");
		public @S(20) TCL_Variable variable;
	}
	
	public static @P(110) class TCL_SignedExpression extends PrimaryOperator
	{
		public @S(10) TCL_PunctuationChoice signedOperator = new TCL_PunctuationChoice("+", "-");
		public @S(20) TCL_Expression expr;
	}

	public static @P(120) class TCL_NotExpression extends PrimaryOperator
	{
		public @S(10) TCL_Keyword NOT = new TCL_Keyword("not");
		public @S(20) TCL_Expression expr;
	}
	
	public static @P(130) class TCL_BangExpression extends PrimaryOperator
	{
		public @S(10) TCL_Punctuation bang = new TCL_Punctuation('!');
		public @S(20) TCL_Expression expr;
	}
	
	public static @P(140) class TCL_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TCL_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(150) class TCL_ArrayExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) TokenList<TCL_Expression> values;
		public @S(30) PunctuationRightBrace rightBrace;
	}

	public static @P(160) class TCL_ExprExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) TCL_Function_Reference function;
		public @S(30) TokenList<TCL_Expression> values;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	///////////////////////////////////////////////
	// Binary expressions
	
	public static @P(500) class TCL_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) TCL_PunctuationChoice operator = new TCL_PunctuationChoice("*", "/", "%");
		public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class TCL_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) TCL_PunctuationChoice operator = new TCL_PunctuationChoice("+", "-");
		public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class TCL_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) TCL_RelOperator relOper;
		public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

		public static class TCL_RelOperator extends TokenChooser
		{
			public @CHOICE TCL_KeywordChoice EQ = new TCL_KeywordChoice("lt", "le", "eq", "ne", "gt", "ge");
			public @CHOICE TCL_PunctuationChoice operator = new TCL_PunctuationChoice(
					"<", ">", "<=", ">=", "==", "<>", "!=");
		}
	}
		
	public static @P(530) class TCL_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) TCL_AndOperator andOper;
		public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class TCL_AndOperator extends TokenChooser
		{
			public @CHOICE TCL_Keyword AND = new TCL_Keyword("and");
			public @CHOICE TCL_Punctuation and = new TCL_Punctuation("&&");
		}
	}
		
	public static @P(540) class TCL_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) TCL_OrOperator orOper;
		public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class TCL_OrOperator extends TokenChooser
		{
			public @CHOICE TCL_Keyword OR = new TCL_Keyword("or");
			public @CHOICE TCL_Punctuation orOper = new TCL_Punctuation("||");
		}
	}
}
