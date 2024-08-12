// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Scala_RangeExpression extends PrecedenceOperator
{
	public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Scala_Keyword TO = new Scala_Keyword("to");
	public @S(30) Scala_Expression right;
}
