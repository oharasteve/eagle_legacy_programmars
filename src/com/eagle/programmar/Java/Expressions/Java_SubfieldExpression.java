// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Java_SubfieldExpression extends PrecedenceOperator
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @OPT @NOSPACE Java_GenericType genericType;
	public @S(40) @NOSPACE Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
}
