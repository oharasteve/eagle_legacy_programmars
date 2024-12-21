// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_HexNumber;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class IntelASM_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) IntelASM_HexNumber hex;
	public @P(20) IntelASM_Number number;
	public @P(30) IntelASM_Literal literal;
	public @P(40) IntelASM_Register register;
	public @P(50) IntelASM_Identifier_Reference var;

	//
	// Note: All operators should stay in @P(#) order. This determines operator
	// precedence.
	//

	public IntelASM_Expression()
	{
		super(_operators);
	}

	public IntelASM_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) static class IntelASM_Brackets extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) IntelASM_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public @P(110) static class IntelASM_BYTEPTR extends PrimaryOperator
	{
		public @S(10) IntelASM_Keyword BYTE = new IntelASM_Keyword("BYTE");
		public @S(20) IntelASM_Keyword PTR = new IntelASM_Keyword("PTR");
		public @S(30) IntelASM_Expression exp;
	}

	public @P(120) static class IntelASM_DWORDPTR extends PrimaryOperator
	{
		public @S(10) IntelASM_Keyword DWORD = new IntelASM_Keyword("DWORD");
		public @S(20) IntelASM_Keyword PTR = new IntelASM_Keyword("PTR");
		public @S(30) PunctuationLeftBracket leftBracket;
		public @S(40) IntelASM_Expression expr;
		public @S(50) PunctuationRightBracket rightBracket;
	}
	
	public @P(130) static class IntelASM_Dollar extends PrimaryOperator
	{
		public @S(10) IntelASM_Punctuation dollar = new IntelASM_Punctuation('$');
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(1000) class IntelASM_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) IntelASM_Expression left = new IntelASM_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) IntelASM_PunctuationChoice operator = new IntelASM_PunctuationChoice("*");
		public @S(30) IntelASM_Expression right = new IntelASM_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(1010) class IntelASM_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) IntelASM_Expression left = new IntelASM_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) IntelASM_PunctuationChoice operator = new IntelASM_PunctuationChoice("+", "-");
		public @S(30) IntelASM_Expression right = new IntelASM_Expression(this, AllowedPrecedence.HIGHER);
	}
}
