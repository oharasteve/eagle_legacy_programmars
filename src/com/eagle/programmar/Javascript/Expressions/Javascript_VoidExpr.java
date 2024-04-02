// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_VoidExpr extends PrimaryOperator
{
	public @S(10) Javascript_Keyword VOID = new Javascript_Keyword("void");
	public @S(20) Javascript_Number number;
}
