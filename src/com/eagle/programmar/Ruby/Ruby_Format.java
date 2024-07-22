// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2024

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleInterpreter;

public class Ruby_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		String txt = fmt.replaceAll("\"", "");
		if (txt.indexOf("#{") < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf("#{", sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more #{
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}

			// Extract a variable name (or expression) and value
			int second = txt.indexOf('}', first + 2);
			if (second < 0) throw new RuntimeException("Missing } in " + txt);
			String var = txt.substring(first + 2, second);
			// System.out.println("***** piece=" + var + " ******");
			Ruby_Expression expr = new Ruby_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			// System.out.println("***** val=" + val + " ******");
			sb.append(val);

			// Look for the next piece
			sc = second + 1;
		}
		return sb.toString();
	}
}
