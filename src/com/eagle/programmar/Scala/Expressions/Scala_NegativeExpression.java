// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Scala_NegativeExpression extends PrimaryOperator
{
	public @S(10) Scala_PunctuationChoice operator = new Scala_PunctuationChoice("-");
	public @S(20) Scala_Expression expr;
}
