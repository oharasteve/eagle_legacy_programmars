// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Go_MultiplicativeExpression extends PrecedenceOperator
{
	public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("*", "/", "%");
	public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
}
