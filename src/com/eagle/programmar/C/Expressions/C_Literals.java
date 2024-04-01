// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class C_Literals extends PrimaryOperator
{
	public @S(10) TokenList<C_Literal> literals;
}
