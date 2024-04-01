// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Java_DotClass extends PrimaryOperator
{
	public @S(10) Java_Type jtype;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword CLASS = new Java_Keyword("class");
}
