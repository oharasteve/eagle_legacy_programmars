// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Fortran_RelationalExpression extends PrecedenceOperator
{
	public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("<", ">", "<=", ">=");
	public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);
}