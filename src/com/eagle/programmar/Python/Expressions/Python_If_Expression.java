// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Python_If_Expression extends PrecedenceOperator 
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Keyword IF = new Python_Keyword("if");
	public @S(30) Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
}