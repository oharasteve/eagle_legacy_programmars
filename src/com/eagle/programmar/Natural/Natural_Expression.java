// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Expressions.Natural_AdditiveExpression;
import com.eagle.programmar.Natural.Expressions.Natural_Function_Call;
import com.eagle.programmar.Natural.Expressions.Natural_LiteralExpression;
import com.eagle.programmar.Natural.Expressions.Natural_MultiplicativeExpression;
import com.eagle.programmar.Natural.Expressions.Natural_NegativeExpression;
import com.eagle.programmar.Natural.Expressions.Natural_ParenthesizedExpression;
import com.eagle.programmar.Natural.Expressions.Natural_System_Variable;
import com.eagle.programmar.Natural.Expressions.Natural_VariableExpression;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Natural_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Natural_Expression()
	{
		super(_operators);
		setOperators(_operators);
	}

	public Natural_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Natural_Number number;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Natural_LiteralExpression literalExpression;
	public @P(110) Natural_System_Variable system_Variable;
	public @P(120) Natural_NegativeExpression negativeExpression;
	public @P(130) Natural_VariableExpression variableExpression;
	public @P(140) Natural_Function_Call function_Call;
	public @P(150) Natural_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Natural_MultiplicativeExpression multiplicativeExpression;
	public @P(510) Natural_AdditiveExpression additiveExpression;
}