// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Java_InstanceOfExpression extends PrecedenceOperator
{
	public @S(10) Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_Keyword instanceOperator = new Java_Keyword("instanceof");
	public @S(30) Java_Type type;
}
