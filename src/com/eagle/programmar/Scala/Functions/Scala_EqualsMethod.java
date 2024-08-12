// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_EqualsMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression leftExpr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Scala_Keyword EQUALS = new Scala_Keyword("equals");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Scala_Expression rightExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(leftExpr);
		String right = interpreter.getStrValue(rightExpr);
		interpreter.pushBool(left.equals(right));
	}
}
