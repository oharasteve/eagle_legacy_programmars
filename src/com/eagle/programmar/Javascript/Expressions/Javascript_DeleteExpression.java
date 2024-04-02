// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_DeleteExpression extends PrimaryOperator
{
	public @S(10) Javascript_Keyword DELETE = new Javascript_Keyword("delete");
	public @S(20) Javascript_Expression expr;
}
