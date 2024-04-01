// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Java_LogicalNotExpression extends PrimaryOperator
{
	public @S(10) Java_Punctuation logicalNotOperator = new Java_Punctuation('~');
	public @S(20) Java_Expression expr;
}
