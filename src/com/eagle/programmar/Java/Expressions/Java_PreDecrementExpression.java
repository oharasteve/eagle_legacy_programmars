// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Java_PreDecrementExpression extends PrimaryOperator
{
	public @S(10) Java_Punctuation preDecrementOperator = new Java_Punctuation("--");
	public @S(20) @NOSPACE Java_Variable var;
}
