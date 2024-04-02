// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Ruby_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Ruby_Expression var = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Ruby_PunctuationChoice equals = new Ruby_PunctuationChoice(
			":=",
			"*=",
			"/=",
			"%=",
			"+=",
			"-=");
	public @S(30) Ruby_Expression expr;
}
