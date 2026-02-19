// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Int_Function extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Python_Keyword INT = new Python_Keyword("int");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Python_Expression expression;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double x = interpreter.getDoubleValue(expression);
		interpreter.pushInt((int) x);
	}

	public static Python_Expression generateInteger(Python_Expression expr, AbstractToken source)
	{
		Python_Int_Function intFn = new Python_Int_Function();
		intFn.leftParen = new PunctuationLeftParen();
		intFn.rightParen = new PunctuationRightParen();
		if (expr.getWhich() instanceof Python_Parenthesized_Expression)
		{
			// Don't create a second set of parens
			Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) expr.getWhich();
			intFn.expression = parens.list.expr;
		}
		else
		{
			intFn.expression = expr;
		}

		intFn.setTransformationSource(source);
		return Python_Generator.wrapExpression(intFn);
	}
}
