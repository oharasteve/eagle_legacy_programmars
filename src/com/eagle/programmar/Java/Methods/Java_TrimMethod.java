// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2025

package com.eagle.programmar.Java.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_TrimMethod extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword TRIM = new Java_Keyword("trim");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(expression);
		interpreter.pushStr(leftStr.trim());
	}

	public static Java_Expression generateTrim(Java_Expression expr, AbstractToken source)
	{
		Java_TrimMethod trimMeth = new Java_TrimMethod();
		trimMeth.expression = expr;
		trimMeth.dot = new PunctuationPeriod();
		trimMeth.leftParen = new PunctuationLeftParen();
		trimMeth.rightParen = new PunctuationRightParen();

		trimMeth.setTransformationSource(source);
		return Java_Generator.wrapExpression(trimMeth);
	}
}
