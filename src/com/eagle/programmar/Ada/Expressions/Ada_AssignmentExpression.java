// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Ada_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Ada_Expression var = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Ada_PunctuationChoice equals = new Ada_PunctuationChoice(
			":=",
			"*=",
			"+=",
			"-=");
	public @S(30) Ada_Expression expr;
}
