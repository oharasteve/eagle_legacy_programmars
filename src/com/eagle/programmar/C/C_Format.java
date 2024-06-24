// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;

public class C_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter, SeparatedList<C_Expression, PunctuationComma> args)
	{
		String fmt = interpreter.getStrValue(args.first());
		fmt = fmt.replaceAll("\\\\n", "");
		if (fmt.indexOf('%') < 0) return fmt;
		
		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = args.getPrimaryCount();
		while (sc < nc)
		{
			// Pull in a text string
			int pct = fmt.indexOf('%', sc);
			if (pct < 0)
			{
				sb.append(fmt.substring(sc, nc));
				break; // Done -- no more %
			}
			if (pct > sc)
			{
				sb.append(fmt.substring(sc, pct));
			}

			// Insert a variable name (or expression)
			index++;
			if (index < numArgs)
			{
				C_Expression expr = args.getPrimaryElement(index);
				String val = interpreter.getStrValue(expr);
				sb.append(val);
			}

			// Look for the next piece
			sc = pct + 2;
		}
		return sb.toString();
	}
}
