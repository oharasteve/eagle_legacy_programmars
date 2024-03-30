// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Ada_PreIncrementExpression extends PrimaryOperator
{
	public @S(10) Ada_PunctuationChoice preIncrementOperator = new Ada_PunctuationChoice("++", "--");
	public @S(20) Ada_Variable var;
}
