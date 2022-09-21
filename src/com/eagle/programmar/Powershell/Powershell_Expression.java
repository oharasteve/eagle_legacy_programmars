// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Statements.Powershell_Command;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Number;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
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

	public static class Powershell_Library extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Powershell_Identifier_Reference,PunctuationPeriod> name;
		public @S(30) @OPT Powershell_Keyword FTP = new Powershell_Keyword("+FTP");
		public @S(40) PunctuationRightBracket rightBracket;
		public @S(50) Powershell_Punctuation colons = new Powershell_Punctuation("::");
	}

	public static class Powershell_Expressions extends TokenSequence
	{
		public @S(10) @OPT Powershell_EndOfLine eoln1;
		public @S(20) Powershell_Expression expr;
		public @S(30) @OPT TokenList<Powershell_MoreExpressions> more;
		public @S(40) @OPT Powershell_Comment comment;
		public @S(50) @OPT Powershell_EndOfLine eoln2;
		
		public static class Powershell_MoreExpressions extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT Powershell_Comment comment;
			public @S(30) @OPT Powershell_EndOfLine eoln;
			public @S(40) Powershell_Expression expr;
		}
	}

	public static class Powershell_DictionaryValues extends TokenSequence
	{
		public @S(10) @OPT Powershell_EndOfLine eoln1;
		public @S(20) Powershell_Identifier_Reference field;
		public @S(30) PunctuationEquals equals;
		public @S(40) Powershell_Expression expr;
		public @S(50) @OPT TokenList<Powershell_MoreDictExpressions> more;
		public @S(60) @OPT Powershell_Comment comment;
		public @S(70) @OPT Powershell_EndOfLine eoln2;
		
		public static class Powershell_MoreDictExpressions extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT Powershell_Comment comment;
			public @S(30) @OPT Powershell_EndOfLine eoln;
			public @S(40) Powershell_Identifier_Reference field;
			public @S(50) PunctuationEquals equals;
			public @S(60) Powershell_Expression expr;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Powershell_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation preIncrementOperator = new Powershell_Punctuation("++");
		public @S(20) Powershell_Variable var;
	}

	public static @P(110) class Powershell_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation preDecrementOperator = new Powershell_Punctuation("--");
		public @S(20) Powershell_Variable var;
	}
	
	public static @P(120) class Powershell_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Variable var;
		public @S(20) Powershell_Punctuation postIncrementOperator = new Powershell_Punctuation("++");
	}

	public static @P(130) class Powershell_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Variable var;
		public @S(20) Powershell_Punctuation postDecrementOperator = new Powershell_Punctuation("--");
	}
	
	public static @P(140) class Powershell_Negative extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation negative = new Powershell_Punctuation('-');
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(150) class Powershell_BangOp extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation BANG = new Powershell_Punctuation("!");
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(160) class Powershell_NotOp extends PrimaryOperator
	{
		public @S(10) Powershell_Keyword NOT = new Powershell_Keyword("-Not");
		public @S(20) Powershell_Expression expr;
	}
	
	public static @P(170) class Powershell_Lists extends PrimaryOperator
	{
		public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Powershell_Expressions expressions;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public static @P(180) class Powershell_Dictionary extends PrimaryOperator
	{
		public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) @OPT Powershell_DictionaryValues expressions;
		public @S(40) PunctuationRightBrace rightBrace;
	}
	
	public static @P(190) class Powershell_FunctionCall extends PrimaryOperator
	{
		public @S(10) @OPT Powershell_DiscardResult discardResult;
		public @S(20) @OPT Powershell_Library library;
		public @S(30) Powershell_Function_Reference func;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) @OPT Powershell_Expressions arguments;
		public @S(60) PunctuationRightParen rightParen;
		
		public static class Powershell_DiscardResult extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Powershell_Keyword VOID = new Powershell_Keyword("void");
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}
	
	public static @P(200) class Powershell_Cast extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Type type;
		public @S(30) PunctuationRightBracket rightBracket;
		public @S(40) Powershell_Expression expr;
	}

	public static @P(210) class Powershell_EvaluateExpression extends PrimaryOperator
	{
		public @S(10) Powershell_Punctuation dollar = new Powershell_Punctuation("$");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Powershell_Expression expression;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public static @P(220) class Powershell_BuiltIn extends PrimaryOperator
	{
		public @S(10) Powershell_KeywordChoice builtin = new Powershell_KeywordChoice(
				"length");
	}

	public static @P(230) class Powershell_BuiltinVariable extends PrimaryOperator
	{
		// Doesn't work to put this up at the top. Ends up with just a Powershell_Punctuation('\0')
		public @S(10) Powershell_PunctuationChoice dollarQuestion = new Powershell_PunctuationChoice(
				"$?", "$_");
	}
	
	public static @P(240) class Powershell_VariableExpression extends PrimaryOperator
	{
		// Because Powershell_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
		public @S(10) Powershell_Variable variable;
	}
	
	public static @P(250) class Powershell_LibraryVariable extends PrimaryOperator
	{
		public @S(10) Powershell_Library libName;
		public @S(20) Powershell_Identifier_Reference variable;
	}
	
	public static @P(260) class Powershell_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Powershell_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static @P(270) class Powershell_CallCommand extends PrimaryOperator
	{
		public @S(10) Powershell_Command command;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(500) class Powershell_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Powershell_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(510) class Powershell_Subfield extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Powershell_Multiplicative_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("*", "/", "%");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class Powershell_Additive_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("+", "-");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(540) class Powershell_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice(
				"-ceq",
				"-cne",
				"-eq",
				"-ge",
				"-gt",
				"-le",
				"-lt",
				"-ne");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(550) class Powershell_LogicalAnd_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_Keyword operator = new Powershell_Keyword("-and");
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Powershell_LogicalOr_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_Keyword operator = new Powershell_Keyword("-or");
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(570) class Powershell_Match_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice(
				"-cmatch",
				"-contains",
				"-f",
				"-in",
				"-join",
				"-match",
				"-notin",
				"-notmatch",
				"-replace",
				"-split");
		public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(580) class Powershell_IsExpression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Powershell_Keyword IS = new Powershell_Keyword("-is"); 
		public @S(30) PunctuationLeftBracket leftBracket;
		public @S(40) Powershell_Type type;
		public @S(50) PunctuationRightBracket rightBracket;
	}

	public static @P(590) class Powershell_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Powershell_PunctuationChoice equals = new Powershell_PunctuationChoice(
				"=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(600) class Powershell_Comma_Expression extends PrecedenceOperator
	{
		public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationComma comma;
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	}
}
