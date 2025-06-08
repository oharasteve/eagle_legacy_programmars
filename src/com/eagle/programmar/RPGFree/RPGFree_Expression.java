// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree;

import com.eagle.programmar.RPGFree.Expressions.RPGFree_Additive;
import com.eagle.programmar.RPGFree.Expressions.RPGFree_Multiplicative;
import com.eagle.programmar.RPGFree.Expressions.RPGFree_Parentheses;
import com.eagle.programmar.RPGFree.Expressions.RPGFree_VariableExpression;
import com.eagle.programmar.RPGFree.Functions.RPGFree_CharFunction;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Literal;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class RPGFree_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public RPGFree_Expression()
	{
		super(_operators);
	}

	public RPGFree_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) RPGFree_Number number;
	public @P(20) RPGFree_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) RPGFree_CharFunction charFunction;
	public @P(110) RPGFree_VariableExpression var;
	public @P(120) RPGFree_Parentheses parens;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) RPGFree_Multiplicative multiplicative;
	public @P(1010) RPGFree_Additive additive;
}
