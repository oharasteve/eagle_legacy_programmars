// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 30, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class COBOL_SignedExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) COBOL_Punctuation signedOperator = new COBOL_Punctuation("-");
	public @S(20) COBOL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.getIntValue(expr);
		switch (signedOperator.toString())
		{
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + signedOperator);
		}
	}
}
