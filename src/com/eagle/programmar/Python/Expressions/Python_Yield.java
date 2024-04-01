// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Python_Yield extends PrimaryOperator
{
	public @S(10) Python_Keyword YIELD = new Python_Keyword("yield");
	public @S(20) Python_Expression expr;
}
