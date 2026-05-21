// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MathAbsFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) Java_Keyword ABS = new Java_Keyword("abs");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression expression;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(expression);
		interpreter.pushDouble(Math.abs(num));
	}

	public static Java_Expression generateAbsFunc(Java_Expression expr, AbstractToken source)
	{
		// Don't bother if it is a constant and not negative
		AbstractToken which = expr.getWhich();
		if (which instanceof Java_Number)
		{
			Java_Number num = (Java_Number) which;
			try
			{
				int n = Integer.parseInt(num.getValue());
				if (n >= 0) return expr;
			}
			catch (Exception ex)
			{
				// Ignore errors
			}
		}
		
		Java_MathAbsFunc abs = new Java_MathAbsFunc();
		abs.leftParen = new PunctuationLeftParen();
		abs.rightParen = new PunctuationRightParen();
		if (expr.getWhich() instanceof Java_ParenthesizedExpression)
		{
			// Don't create a second set of parens
			Java_ParenthesizedExpression parens = (Java_ParenthesizedExpression) expr.getWhich();
			abs.expression = parens.expression;
		}
		else
		{
			abs.expression = expr;
		}
		
		abs.setTransformationSource(source);
		return Java_MathFunction.wrapMathFunction(abs, source);
	}
}
