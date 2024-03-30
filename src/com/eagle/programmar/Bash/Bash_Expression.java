// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Bash_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Bash_Expression()
	{
	    super(_operators);
	}

	public Bash_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
	
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Bash_Number number;
	public @P(20) Bash_Literal literal;
	
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Bash_DollarNumber extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) Bash_Number number;
	}
	
	public static @P(110) class Bash_DollarPound extends PrimaryOperator
	{
		public @S(10) Bash_PunctuationChoice dollarPound = new Bash_PunctuationChoice("$#", "$?", "$@", "$*");
	}
	
	public static @P(120) class Bash_DollarExpr extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) Bash_Expression exor;
		public @S(40) PunctuationRightBrace rightBrace;
	}

	public static @P(130) class Bash_DollarSubstring extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) Bash_Variable variable;
		public @S(40) PunctuationColon colon1;
		public @S(50) Bash_Expression start;
		public @S(60) PunctuationColon colon2;
		public @S(70) Bash_Expression stop;
		public @S(80) PunctuationRightBrace rightBrace;
	}

	public static @P(140) class Bash_SizeExpression extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation bang = new Bash_Punctuation("#");
		public @S(20) Bash_Variable var;
	}
	
	public static @P(150) class Bash_Array extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TokenList<Bash_Expression> items;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(160) class Bash_VariableExpression extends PrimaryOperator
	{
		// Because Bash_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
		public @S(10) Bash_Variable variable;
	}

	public static @P(170) class Bash_Evaluate1 extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Bash_Statement stmt;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(180) class Bash_Evaluate2 extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) Bash_Punctuation leftParenParen = new Bash_Punctuation("((");
		public @S(30) Bash_Expression expr;
		public @S(40) Bash_Punctuation rightParenParen = new Bash_Punctuation("))");
	}

	public static @P(190) class Bash_Range extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) Bash_Number start;
		public @S(30) Bash_Punctuation dotDot = new Bash_Punctuation("..");
		public @S(40) Bash_Number stop;
		public @S(50) PunctuationRightBrace rightBrace;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Bash_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_PunctuationChoice operator = new Bash_PunctuationChoice("*", "/", "%");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class Bash_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_RelOp operator;
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Bash_RelOp extends TokenChooser
		{
			public @CHOICE Bash_PunctuationChoice strOp = new Bash_PunctuationChoice(
					"==", "!=", "<", ">", "<=", ">=");
			public @CHOICE Bash_KeywordChoice numOp = new Bash_KeywordChoice(
					"-eq", "-ne", "-lt", "-gt", "-le", "-ge");
		}
	}
	
	public static @P(520) class Bash_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_PunctuationChoice operator = new Bash_PunctuationChoice("+", "-");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class Bash_LogicalAnd_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_Punctuation operator = new Bash_Punctuation("&&");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(540) class Bash_LogicalOr_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_Punctuation operator = new Bash_Punctuation("||");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(550) class Bash_Assignment_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}
}
