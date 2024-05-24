// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Commands.Bash_GrepCommand;
import com.eagle.programmar.Bash.Commands.Bash_ReadCommand;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Bash_Condition extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	//
	// Note: All operators should stay in @P(#) order. This determines operator
	// precedence.
	//

	public Bash_Condition()
	{
		super(_operators);
	}

	public Bash_Condition(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class Bash_ExpressionCondition extends PrimaryOperator
	{
		public @S(10) Bash_Expression expression;
	}

	public static @P(110) class Bash_BracketCondition extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Bash_Condition condition;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static @P(120) class Bash_BracketsCondition extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation leftBrackets = new Bash_Punctuation("[[");
		public @S(20) Bash_Condition condition;
		public @S(30) Bash_Punctuation rightBrackets = new Bash_Punctuation("]]");
	}

	public static @P(130) class Bash_ExistsCondition extends PrimaryOperator
	{
		public @S(10) Bash_KeywordChoice E = new Bash_KeywordChoice("-d", "-e", "-f", "-n", "-x", "-z");
		public @S(20) Bash_FilenameOrLiteral filename;
	}

	public static @P(140) class Bash_NotCondition extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation NOT = new Bash_Punctuation("!");
		public @S(20) Bash_Condition condition;
	}

	public static @P(150) class Bash_Constants extends PrimaryOperator
	{
		public @S(10) Bash_KeywordChoice TRUE = new Bash_KeywordChoice("false", "true");
	}

	public static @P(160) class Bash_GrepCondition extends PrimaryOperator
	{
		public @S(10) Bash_GrepCommand grepStatement;
	}

	public static @P(170) class Bash_ReadCondition extends PrimaryOperator
	{
		public @S(10) Bash_ReadCommand readStatement;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Bash_LogicalAnd_Condition extends PrecedenceOperator
	{
		public @S(10) Bash_Condition left = new Bash_Condition(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationHyphen dash;
		public @S(30) Bash_Keyword AND = new Bash_Keyword("and");
		public @S(40) Bash_Condition right = new Bash_Condition(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class Bash_LogicalOr_Condition extends PrecedenceOperator
	{
		public @S(10) Bash_Condition left = new Bash_Condition(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationHyphen dash;
		public @S(30) Bash_Keyword OR = new Bash_Keyword("or");
		public @S(40) Bash_Condition right = new Bash_Condition(this, AllowedPrecedence.HIGHER);
	}
}