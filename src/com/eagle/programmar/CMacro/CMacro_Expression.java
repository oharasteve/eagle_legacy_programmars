// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CMacro;

import com.eagle.programmar.CMacro.Expressions.CMacro_AdditiveExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseAndExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_BitwiseOrExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_ConcatenateExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalAndExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_ConditionalOrExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_EqualityExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_ExclusiveOrExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_FunctionCall;
import com.eagle.programmar.CMacro.Expressions.CMacro_IdentifierExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_MultiplicativeExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_NotExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_ParenthesizedExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_RelationalExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_SignedExpression;
import com.eagle.programmar.CMacro.Expressions.CMacro_SymbolExpression;
import com.eagle.programmar.CMacro.Terminals.CMacro_Character_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_HexNumber;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class CMacro_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public CMacro_Expression()
	{
		super(_operators);
	}

	public CMacro_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) CMacro_HexNumber hex;
	public @P(20) CMacro_Number number;
	public @P(30) CMacro_Literal literal;
	public @P(40) CMacro_Character_Literal characters;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) CMacro_FunctionCall functionCall;
	public @P(110) CMacro_IdentifierExpression identifierExpression;
	public @P(120) CMacro_SignedExpression signedExpression;
	public @P(130) CMacro_NotExpression notExpression;
	public @P(140) CMacro_ParenthesizedExpression parenthesizedExpression;
	public @P(150) CMacro_SymbolExpression symbolExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) CMacro_MultiplicativeExpression multiplicativeExpression;
	public @P(510) CMacro_AdditiveExpression additiveExpression;
	public @P(520) CMacro_RelationalExpression relationalExpression;
	public @P(530) CMacro_EqualityExpression equalityExpression;
	public @P(540) CMacro_BitwiseAndExpression bitwiseAndExpression;
	public @P(550) CMacro_ExclusiveOrExpression exclusiveOrExpression;
	public @P(560) CMacro_BitwiseOrExpression bitwiseOrExpression;
	public @P(570) CMacro_ConditionalAndExpression conditionalAndExpression;
	public @P(580) CMacro_ConditionalOrExpression conditionalOrExpression;
	public @P(590) CMacro_ConcatenateExpression concatenateExpression;
}
