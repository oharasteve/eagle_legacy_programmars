// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_Power_Expression extends PrecedenceOperator
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Javascript_Punctuation stars = new Javascript_Punctuation("**");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
}
