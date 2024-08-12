// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_PrintLnFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_Keyword PRINTLN = new Scala_Keyword("println");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String line = interpreter.getStrValue(expr);
		System.out.println(line);
	}
}
