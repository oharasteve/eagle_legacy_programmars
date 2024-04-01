// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Fortran_VariableExpression extends PrimaryOperator
{
	public @S(10) Fortran_Variable variable;
}