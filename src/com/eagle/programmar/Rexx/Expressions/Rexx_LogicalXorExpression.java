// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Rexx_LogicalXorExpression extends PrecedenceOperator
{
	public @S(10) Rexx_Expression left = new Rexx_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rexx_Punctuation AND = new Rexx_Punctuation("&&");
	public @S(30) Rexx_Expression right = new Rexx_Expression(this, AllowedPrecedence.HIGHER);
}
