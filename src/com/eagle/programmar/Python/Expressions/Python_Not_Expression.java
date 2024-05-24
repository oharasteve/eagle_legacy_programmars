// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Python_Not_Expression extends PrimaryOperator
{
	public @S(10) Python_Keyword NOT = new Python_Keyword("not");
	public @S(20) Python_Expression expr;
}
