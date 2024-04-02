// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Julia_MultiplicativeExpression extends PrecedenceOperator
{
	public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("*", "/", "%");
	public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);
}
