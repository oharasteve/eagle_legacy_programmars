// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Java_TrueFalseExpression extends PrecedenceOperator
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Java_Punctuation questionMark = new Java_Punctuation('?');
	public @S(30) Java_Expression middle = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(40) PunctuationColon colon;
	public @S(50) Java_Expression right = new Java_Expression(this, AllowedPrecedence.ATLEAST);
}
