// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Scala_PostIncrementExpression extends PrimaryOperator
{
	public @S(10) Scala_Variable var;
	public @S(20) Scala_PunctuationChoice postIncrementOperator = new Scala_PunctuationChoice("++", "--");
}
