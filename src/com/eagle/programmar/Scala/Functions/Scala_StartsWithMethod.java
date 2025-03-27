// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_StartsWithMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression leftExpr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Scala_Keyword STARTSWITH = new Scala_Keyword("startswith");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Scala_Expression rightExpr;
	public @S(60) @OPT PunctuationComma comma;
	public @S(70) @OPT Scala_Expression scExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(leftExpr);
		String right = interpreter.getStrValue(rightExpr);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushBool(left.startsWith(right, sc));
		}
		else
		{
			interpreter.pushBool(left.startsWith(right));
		}
	}
}
