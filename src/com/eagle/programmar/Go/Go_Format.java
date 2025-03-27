// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Go;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Go_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter, SeparatedList<Go_Expression, PunctuationComma> arguments)
	{
		int paramCount = arguments.getPrimaryCount();
		if (paramCount == 0) return "";
		String fmt = interpreter.getStrValue(arguments.getPrimaryElement(0));
		if (fmt.indexOf('%') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 1;
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
			if (index < paramCount)
			{
				String piece = interpreter.getStrValue(arguments.getPrimaryElement(index));
				sb.append(piece);
			}
			index++;

			// Look for the next piece
			sc = pct + 2;
		}
		return sb.toString();
	}
}
