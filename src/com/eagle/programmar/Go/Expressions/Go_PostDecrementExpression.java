// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Go_PostDecrementExpression extends PrimaryOperator
{
	public @S(10) Go_Variable var;
	public @S(20) Go_Punctuation postDecrementOperator = new Go_Punctuation("--");
}
