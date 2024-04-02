// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_InstanceOfExpression extends PrecedenceOperator
{
	public @S(10) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
	public @S(30) Javascript_Type type;
}
