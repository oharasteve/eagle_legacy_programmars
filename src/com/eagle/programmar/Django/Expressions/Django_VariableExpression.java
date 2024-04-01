// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Django_VariableExpression extends PrimaryOperator
{
	public @S(10) Django_Variable variable;
}
