// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Java_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Java_PunctuationChoice equals = new Java_PunctuationChoice(
			"=",
			"*=",
			"/=",
			"%=",
			"+=",
			"-=",
			"<<=",
			">>=",
			">>>=",
			"&=",
			"^=",
			"|=");
	public @S(30) Java_Expression expr;
}
