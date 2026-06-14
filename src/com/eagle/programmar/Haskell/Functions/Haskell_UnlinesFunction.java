// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_UnlinesFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Haskell_Keyword UNLINES = new Haskell_Keyword("unlines");
	public @S(20) Haskell_Expression expr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			StringBuffer sb = new StringBuffer();
			for (EagleValue val : array.getArrayValue())
			{
				if (!sb.isEmpty()) sb.append("\n");
				sb.append(val.forceStringValue());
			}
			interpreter.pushStr(sb.toString());
		}
		else
		{
			interpreter.pushEagleValue(value);
		}
	}
}
