// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Expressions;

import com.eagle.programmar.RPGFree.RPGFree_Expression;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class RPGFree_Additive extends PrecedenceOperator
{
	public @S(10) RPGFree_Expression left = new RPGFree_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) RPGFree_PunctuationChoice operator = new RPGFree_PunctuationChoice("+", "-");
	public @S(30) RPGFree_Expression right = new RPGFree_Expression(this, AllowedPrecedence.HIGHER);
}
