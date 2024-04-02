// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_NegativeExpression extends PrimaryOperator
{
	public @S(10) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
	public @S(20) Javascript_Expression expr;
}
