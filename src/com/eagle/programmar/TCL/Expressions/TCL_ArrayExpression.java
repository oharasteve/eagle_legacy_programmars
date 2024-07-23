// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_ArrayExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) TokenList<TCL_Expression> exprs;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (TCL_Expression expr : exprs._elements)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			vals.addValue(val);
			if (interpreter._TRACE) System.err.println("*** array += " + val.toString());
		}

		interpreter.pushEagleValue(vals);
	}
}
