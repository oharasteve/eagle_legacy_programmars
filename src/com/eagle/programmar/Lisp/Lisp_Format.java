// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.Lisp;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.TokenList;

public class Lisp_Format
{
	// Handle ~d and ~A. Super simple ones only for now
	public static String format(EagleInterpreter interpreter, TokenList<Lisp_Expression> exprs)
	{
		String fmt = interpreter.getStrValue(exprs._elements.get(0));
		fmt = fmt.replaceAll("~%", ""); // Remove newlines from format
		if (fmt.indexOf('~') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = exprs._elements.size();
		while (sc < nc)
		{
			// Pull in a text string
			int tilde = fmt.indexOf('~', sc);
			if (tilde < 0)
			{
				sb.append(fmt.substring(sc, nc));
				break; // Done -- no more ~
			}
			if (tilde > sc)
			{
				sb.append(fmt.substring(sc, tilde));
			}

			// Insert a variable name (or expression)
			index++;
			if (index < numArgs)
			{
				Lisp_Expression expr = exprs._elements.get(index);
				String val = interpreter.getStrValue(expr);
				sb.append(val);
			}

			// Look for the next piece
			sc = tilde + 2;
		}
		return sb.toString();
	}
}
