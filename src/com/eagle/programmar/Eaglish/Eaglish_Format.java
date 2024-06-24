// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleInterpreter;

public class Eaglish_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		String txt = fmt.replaceAll("\"", "");
		if (txt.indexOf('^') < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf('^', sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more ^
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}

			// Extract a variable name (or expression) and value
			int second = txt.indexOf('^', first + 1);
			if (second < 0) throw new RuntimeException("Missing ^ in " + txt);
			String var = txt.substring(first + 1, second);
			Eaglish_Expression expr = new Eaglish_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			sb.append(val);

			// Look for the next piece
			sc = second + 1;
		}
		return sb.toString();
	}
}
