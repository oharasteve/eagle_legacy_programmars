// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Java_PostDecrementExpression extends PrimaryOperator
{
	public @S(10) Java_Variable var;
	public @S(20) @NOSPACE Java_Punctuation postDecrementOperator = new Java_Punctuation("--");
}
