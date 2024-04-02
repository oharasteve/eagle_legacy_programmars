// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class TCL_NotExpression extends PrimaryOperator
{
	public @S(10) TCL_Keyword NOT = new TCL_Keyword("not");
	public @S(20) TCL_Expression expr;
}
