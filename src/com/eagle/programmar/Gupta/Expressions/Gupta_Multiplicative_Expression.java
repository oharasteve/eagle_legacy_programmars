// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Gupta.Expressions;

import com.eagle.programmar.Gupta.Gupta_Expression;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Gupta_Multiplicative_Expression extends PrecedenceOperator
{
	public @S(10) Gupta_Expression left = new Gupta_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Gupta_PunctuationChoice timesDivide = new Gupta_PunctuationChoice("*", "/");
	public @S(30) Gupta_Expression right = new Gupta_Expression(this, AllowedPrecedence.HIGHER);
}
