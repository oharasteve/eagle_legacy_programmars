// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class CMacro_BitwiseOrExpression extends PrecedenceOperator
{
	public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMacro_Punctuation bitwiseOrOperator = new CMacro_Punctuation('|');
	public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
}
