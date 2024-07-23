// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_ArrayExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) VB_Keyword ARRAY = new VB_Keyword("Array");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<VB_Expression, PunctuationComma> exprs;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(exprs.getPrimaryElement(i));
			vals.addValue(val);
			// if (interpreter._TRACE) System.err.println("*** array += " + val.toString());
		}

		interpreter.pushEagleValue(vals);
	}
}