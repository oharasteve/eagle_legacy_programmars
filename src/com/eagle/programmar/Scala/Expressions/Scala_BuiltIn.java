// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Scala_BuiltIn extends PrimaryOperator
{
	public @S(10) Scala_KeywordChoice builtinConstant = new Scala_KeywordChoice("false", "true");
}
