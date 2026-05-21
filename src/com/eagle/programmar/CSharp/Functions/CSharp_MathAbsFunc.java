// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 6, 2026

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MathAbsFunc extends TokenSequence implements EagleRunnable
{
	public @S(10) CSharp_Keyword ABS = new CSharp_Keyword("Abs");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression number;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getDoubleValue(number);
		interpreter.pushDouble(Math.abs(num));
	}

	public static CSharp_Expression generateAbsFunc(AbstractExpression number, AbstractToken source)
	{
		CSharp_MathAbsFunc pow = new CSharp_MathAbsFunc();
		pow.leftParen = new PunctuationLeftParen();
		pow.number = (CSharp_Expression) number;
		pow.rightParen = new PunctuationRightParen();
		pow.setTransformationSource(source);
		return CSharp_MathFunction.wrapMathFunction(pow, source);
	}
}
