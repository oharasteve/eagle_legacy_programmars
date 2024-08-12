// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Scala_ReverseMethod extends PrecedenceOperator
{
	public @S(10) Scala_Expression leftExpr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Scala_Keyword REVERSE = new Scala_Keyword("reverse");
}
