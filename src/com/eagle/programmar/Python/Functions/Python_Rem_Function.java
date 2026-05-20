// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2026

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Rem_Function extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Python_Keyword MATH = new Python_Keyword("math");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Keyword FMOD = new Python_Keyword("fmod");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Python_Expression leftExpr;
	public @S(60) @NOSPACE PunctuationComma comma;
	public @S(70) @NOSPACE Python_Expression rightExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(leftExpr);
		int y = interpreter.getIntValue(rightExpr);
		interpreter.pushInt(x % y);
	}

	public static Python_Expression generateRemFunc(Python_Expression numer,
			Python_Expression denom, AbstractToken source)
	{
		Python_Rem_Function remFn = new Python_Rem_Function();
		remFn.dot = new PunctuationPeriod();
		remFn.leftParen = new PunctuationLeftParen();
		remFn.leftExpr = numer;
		remFn.comma = new PunctuationComma();
		remFn.rightExpr = denom;
		remFn.rightParen = new PunctuationRightParen();

		remFn.setTransformationSource(source);
		Python_Expression remExpr = Python_Generator.wrapExpression(remFn);
		return Python_Int_Function.generateInteger(remExpr, source);
	}
}
