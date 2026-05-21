// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 6, 2026

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MathAbsFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) CSharp_Keyword ABS = new CSharp_Keyword("Abs");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression expression;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(expression);
		interpreter.pushDouble(Math.abs(num));
	}

	public static CSharp_Expression generateAbsFunc(CSharp_Expression expr, AbstractToken source)
	{
		// Don't bother if it is a constant and not negative
		AbstractToken which = expr.getWhich();
		if (which instanceof CSharp_Number)
		{
			CSharp_Number num = (CSharp_Number) which;
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
		
		CSharp_MathAbsFunc abs = new CSharp_MathAbsFunc();
		abs.leftParen = new PunctuationLeftParen();
		abs.expression = expr;
		abs.rightParen = new PunctuationRightParen();
		if (expr.getWhich() instanceof CSharp_ParenthesizedExpression)
		{
			// Don't create a second set of parens
			CSharp_ParenthesizedExpression parens = (CSharp_ParenthesizedExpression) expr.getWhich();
			abs.expression = parens.expression;
		}
		else
		{
			abs.expression = expr;
		}

		abs.setTransformationSource(source);
		return CSharp_MathFunction.wrapMathFunction(abs, source);
	}
}
