// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class TCL_VariableExpression extends PrimaryOperator
{
	public @S(10) @OPT TCL_Punctuation dollar = new TCL_Punctuation("$");
	public @S(20) TCL_Variable variable;
}
