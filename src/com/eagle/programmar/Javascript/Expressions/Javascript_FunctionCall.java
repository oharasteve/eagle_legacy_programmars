// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_FunctionCall extends PrimaryOperator
{
	public @S(10) Javascript_Variable functionName;
	public @S(20) Javascript_ParenthesizedExpression arguments;
}
