// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Java_ColonColonType extends PrimaryOperator
{
	public @S(10) Java_Type left;
	public @S(20) @NOSPACE Java_Punctuation colonColon = new Java_Punctuation("::");
	public @S(30) @NOSPACE Java_Expression right;
}
