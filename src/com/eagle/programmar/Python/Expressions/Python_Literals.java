// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Python_Literals extends PrimaryOperator
{
	public @S(10) TokenList<Python_Literal> literals;
}
