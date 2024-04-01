// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CMacro_SymbolExpression extends PrimaryOperator
{
	public @S(10) CMacro_Punctuation poundOperator = new CMacro_Punctuation('#');
	public @S(20) CMacro_Expression expr;
}
