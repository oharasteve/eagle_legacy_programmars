// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Bash_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Bash_Number number;
	public @P(20) Bash_Literal literal;
	
	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Bash_Expression()
	{
	    super(_operators);
	}

	public Bash_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Bash_DollarNumber extends PrimaryOperator
	{
		public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
		public @S(20) Bash_Number number;
	}
	
	public static @P(110) class Bash_Array extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TokenList<Bash_Expression> items;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(120) class Bash_VariableExpression extends PrimaryOperator
	{
		// Because Bash_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
		public @S(10) Bash_Variable variable;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(430) class Bash_Relational_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_RelOp operator;
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Bash_RelOp extends TokenChooser
		{
			public @CHOICE Bash_PunctuationChoice strOp = new Bash_PunctuationChoice(
					"==", "!=", "<", ">", "<=", ">=");
			public @CHOICE Bash_KeywordChoice numOp = new Bash_KeywordChoice(
					"-eq", "-ne", "-lt", "-gt", "-lt", "-le");
		}
	}
	
	public static @P(440) class Bash_LogicalAnd_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_Punctuation operator = new Bash_Punctuation("&&");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(450) class Bash_LogicalOr_Expression extends PrecedenceOperator
	{
		public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Bash_Punctuation operator = new Bash_Punctuation("||");
		public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);
	}
}
