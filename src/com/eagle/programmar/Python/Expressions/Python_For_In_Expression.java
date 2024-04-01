// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Python_For_In_Expression extends PrecedenceOperator
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(30) Python_Keyword FOR = new Python_Keyword("for");
	public @S(40) Python_VariableList varList;
	public @S(50) Python_Keyword IN = new Python_Keyword("in");
	public @S(60) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
}
