// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Algol68_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Algol68_Expression var = new Algol68_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Algol68_PunctuationChoice equals = new Algol68_PunctuationChoice(
			":=",
			"*=",
			"+=",
			"-=");
	public @S(30) Algol68_Expression expr;
}
