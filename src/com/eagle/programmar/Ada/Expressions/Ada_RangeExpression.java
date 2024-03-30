// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Ada_RangeExpression extends PrecedenceOperator
{
	public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ada_Punctuation dotDot = new Ada_Punctuation("..");
	public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
}
