// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 24, 2024

package com.eagle.programmar.FSharp;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.TokenList;

public class FSharp_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter, TokenList<FSharp_Expression> args)
	{
		String fmt = interpreter.getStrValue(args.first());
		fmt = fmt.replaceAll("\\\\n", "");
		if (fmt.indexOf('%') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = args.size();
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
				FSharp_Expression expr = args._elements.get(index);
				String val = interpreter.getStrValue(expr);
				sb.append(val);
			}

			// Look for the next piece
			sc = pct + 2;
		}
		return sb.toString();
	}
}
