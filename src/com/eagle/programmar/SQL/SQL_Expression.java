// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
import com.eagle.programmar.SQL.Statements.SQL_ValuesStatement;
import com.eagle.programmar.SQL.Statements.SQL_WithStatement;
import com.eagle.programmar.SQL.Terminals.SQL_HexString;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class SQL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) SQL_Number number;
	public @P(20) SQL_Literal literal;
	public @P(30) SQL_HexString hex;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public SQL_Expression()
	{
	    super(_operators);
	}

	public SQL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class SQL_CurrentTimeStamp extends PrimaryOperator
	{
		// For some reason, this sometimes has parens after it
		public @S(10) SQL_Keyword TIMESTAMP = new SQL_Keyword("CURRENT_TIMESTAMP");
		public @S(20) @OPT SQL_CurrentTimeStampFunction func;
		
		public static class SQL_CurrentTimeStampFunction extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PunctuationRightParen rightParen;
		}
	}
	
	public static @P(110) class SQL_Builtin extends PrimaryOperator
	{
		public @S(10) SQL_KeywordChoice SYSTIMESTAMP = new SQL_KeywordChoice(
				"FALSE",
				"NULL",
				"SYSTIMESTAMP",
				"TRUE");
	}
	
	public static @P(120) class SQL_FunctionExpression extends PrimaryOperator
	{
		public @S(10) SQL_Function function;
	}

	public static @P(130) class SQL_CastExpression extends PrimaryOperator
	{
		public @S(10) SQL_Keyword CAST = new SQL_Keyword("CAST");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SQL_Expression expr;
		public @S(40) SQL_Keyword AS = new SQL_Keyword("AS");
		public @S(50) SQL_Type type;
		public @S(60) PunctuationRightParen rightParen;
	}
	
	public static @P(140) class SQL_DollarVariable extends PrimaryOperator
	{
		public @S(10) SQL_Punctuation dollar = new SQL_Punctuation('$');
		public @S(20) SQL_Number number;
	}
	
	public static @P(150) class SQL_VariableExpression extends PrimaryOperator
	{
		public @S(10) SQL_Variable variable;
	}

	public static @P(160) class SQL_Star extends PrimaryOperator
	{
		public @S(10) PunctuationStar star;
	}
	
	public static @P(170) class SQL_InnerSelect extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SQL_SelectStatement innerSelect;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(180) class SQL_InnerValues extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SQL_ValuesStatement innerValues;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(190) class SQL_InnerWith extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SQL_WithStatement innerWith;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static @P(200) class SQL_Parentheses extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<SQL_Expression,PunctuationComma> exprs;
		public @S(30) PunctuationRightParen rightParen;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class SQL_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) SQL_PunctuationChoice operator = new SQL_PunctuationChoice("*", "/", "%");
		public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class SQL_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) SQL_PunctuationChoice operator = new SQL_PunctuationChoice("+", "-");
		public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class SQL_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) SQL_RelationalOperator relationalOperator;
		public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

		public static class SQL_RelationalOperator extends TokenChooser
		{
			public @CHOICE SQL_KeywordChoice LIKE = new SQL_KeywordChoice("LIKE", "IN");
			public @CHOICE SQL_PunctuationChoice operator = new SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
		}
	}

	public static @P(530) class SQL_AndExpression extends PrecedenceOperator
	{
		public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) SQL_Keyword AND = new SQL_Keyword("AND");
		public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class SQL_OrExpression extends PrecedenceOperator
	{
		public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) SQL_Keyword OR = new SQL_Keyword("OR");
		public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);
	}
}
