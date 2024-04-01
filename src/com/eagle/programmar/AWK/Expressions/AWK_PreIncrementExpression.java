// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class AWK_PreIncrementExpression extends PrimaryOperator
{
	public @S(10) AWK_Punctuation operator = new AWK_Punctuation("++");
	public @S(20) AWK_Expression expr;
}
