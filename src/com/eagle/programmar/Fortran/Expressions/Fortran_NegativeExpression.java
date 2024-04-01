// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Fortran_NegativeExpression extends PrimaryOperator
{
	public @S(10) Fortran_PunctuationChoice operator = new Fortran_PunctuationChoice("-");
	public @S(20) Fortran_Expression expr;
}
