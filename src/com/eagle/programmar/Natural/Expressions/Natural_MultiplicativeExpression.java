// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Natural.Expressions;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Natural_MultiplicativeExpression extends PrecedenceOperator
{
	public @S(10) Natural_Expression left = new Natural_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Natural_PunctuationChoice timesDivide = new Natural_PunctuationChoice("*", "/");
	public @S(30) Natural_Expression right = new Natural_Expression(this, AllowedPrecedence.HIGHER);
}
