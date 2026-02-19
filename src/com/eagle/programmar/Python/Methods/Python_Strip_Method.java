// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2025

package com.eagle.programmar.Python.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Strip_Method extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Python_Expression expression = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Keyword STRIP = new Python_Keyword("strip");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String line = interpreter.getStrValue(expression);
		interpreter.pushInt(line.length());
	}

	public static Python_Expression generateTrim(Python_Expression expr, AbstractToken source)
	{
		Python_Strip_Method stripMeth = new Python_Strip_Method();
		stripMeth.dot = new PunctuationPeriod();
		stripMeth.leftParen = new PunctuationLeftParen();
		stripMeth.expression = expr;
		stripMeth.rightParen = new PunctuationRightParen();

		stripMeth.setTransformationSource(source);
		return Python_Generator.wrapExpression(stripMeth);
	}
}
