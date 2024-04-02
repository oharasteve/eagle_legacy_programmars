// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Go_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Go_Expression var = new Go_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice(
			":=",
			"*=",
			"/=",
			"%=",
			"+=",
			"-=");
	public @S(30) Go_Expression expr;
}
