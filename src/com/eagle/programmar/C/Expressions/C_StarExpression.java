// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_StarExpression extends PrimaryOperator
{
	public @S(10) PunctuationStar star;
	public @S(20) C_Expression expr;
}
