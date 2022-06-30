// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Identifier;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Number;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Expression extends PrecedenceChooser
{
	protected static OperatorList _operators = new OperatorList();

	public @P(10) Powershell_Number number;
	public @P(20) Powershell_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Powershell_Expression()
	{
	    super(_operators);
	}

	public Powershell_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Powershell_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation preIncrementOperator = new Powershell_Punctuation("++");
		public @S(20) @NOSPACE Powershell_Variable var;
	}

	public static @P(110) class Powershell_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation preDecrementOperator = new Powershell_Punctuation("--");
		public @S(20) @NOSPACE Powershell_Variable var;
	}
	
	public static @P(120) class Powershell_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Variable var;
		public @S(20) @NOSPACE Powershell_Punctuation postIncrementOperator = new Powershell_Punctuation("++");
	}

	public static @P(130) class Powershell_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Variable var;
		public @S(20) @NOSPACE Powershell_Punctuation postDecrementOperator = new Powershell_Punctuation("--");
	}
	
	public static @P(140) class Powershell_Negative extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation negative = new Powershell_Punctuation('-');
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(150) class Powershell_NotOp extends PrimaryOperator
	{
		public @S(10) Powershell_Keyword NOT = new Powershell_Keyword("-Not");
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(160) class Powershell_Lists extends PrimaryOperator
	{
		public @S(19) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Powershell_Expression expr;
		public @S(40) TokenList<Powershell_ListItem> listItem;
		public @S(50) PunctuationRightParen rightParen;
		
		public static class Powershell_ListItem extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) Powershell_Expression expr;
		}
	}
	
	public static @P(170) class Powershell_LibraryCall extends PrimaryOperator
	{
		public @S(10) @OPT Powershell_Library library;
		public @S(20) Powershell_Function_Reference func;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) SeparatedList<Powershell_Expression, PunctuationComma> arguments;
		public @S(50) PunctuationRightParen rightParen;
		
		public static class Powershell_Library extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Powershell_KeywordChoice MATH = new Powershell_KeywordChoice("Math");
			public @S(30) PunctuationRightBracket rightBracket;
			public @S(40) Powershell_Punctuation colons = new Powershell_Punctuation("::");
		}
	}
	
	public static @P(180) class Powershell_Cast extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Type type;
		public @S(30) PunctuationRightBracket rightBracket;
		public @S(40) Powershell_Expression expr;
	}

	public static @P(190) class Powershell_EvaluateExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation dollar = new Powershell_Punctuation("$");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @NOSPACE Powershell_Expression expression;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(200) class Powershell_BuiltIn extends PrimaryOperator
	{
		public @S(10) Powershell_KeywordChoice builtin = new Powershell_KeywordChoice(
				"length");
	}

	public static @P(220) class Powershell_VariableExpression extends PrimaryOperator
	{
		// Because Powershell_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
		public @S(10) Powershell_Variable variable;
	}
	
	public static @P(230) class Powershell_FilenameExpression extends PrimaryOperator
	{
		// Because Powershell_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
		public @S(10) Powershell_Filename filename;
	}
	
	public static @P(240) class Powershell_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @NOSPACE Powershell_Expression expression;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(250) class Powershell_CallExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Identifier id;
		public @S(20) TokenList<Powershell_Expression> arguments;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(400) class Powershell_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Powershell_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(405) class Powershell_Subfield extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(410) class Powershell_Multiplicative_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("*", "/", "%");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Powershell_Additive_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("+", "-");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(430) class Powershell_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-lt", "-le");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(440) class Powershell_LogicalAnd_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_Keyword operator = new Powershell_Keyword("-and");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(450) class Powershell_LogicalOr_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_Keyword operator = new Powershell_Keyword("-or");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(460) class Powershell_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Powershell_PunctuationChoice equals = new Powershell_PunctuationChoice(
				"=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Powershell_Expression expr;
	}
}
