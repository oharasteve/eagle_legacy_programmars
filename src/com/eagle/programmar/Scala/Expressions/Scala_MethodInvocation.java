// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_MethodInvocation extends PrimaryOperator
{
	public @S(10) Scala_Variable methodName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Scala_Expression,PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;
}
