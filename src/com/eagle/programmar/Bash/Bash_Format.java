// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;

public class Bash_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		if (fmt.startsWith("'"))
		{
			return fmt;
		}

		String txt = fmt;
		if (txt.startsWith("\""))
		{
			// Toss leading and trailing quotes, if present
			txt = txt.substring(1, txt.length()-1);
		}
		
		// Change \" to "
		txt = txt.replaceAll("\\\\", "\\");
		
		if (txt.indexOf('$') < 0)
		{
			return txt;
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf('$', sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more $
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}
			if (first + 1 < nc && txt.charAt(first + 1) == '{')
			{
				// Extract an expression
				int second = txt.indexOf("}", first + 2);
				if (second < 0) throw new RuntimeException("Missing } in " + txt);
				String var = txt.substring(first + 2, second);
				Bash_Expression expr = new Bash_Expression();
				if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
				{
					throw new RuntimeException("Unable to parse expression " + var);
				}
				String val = interpreter.getStrValue(expr);
				sb.append(val);

				// Look for the next piece
				sc = second + 1;
			}
			else
			{
				// Just a variable, like $str
				int second = first + 1;
				while (second < nc)
				{
					char ch = txt.charAt(second);
					if (!Character.isLetterOrDigit(ch)) break;
					second++;
				}
				if (first + 1 == second)
				{
					// Just a lonely $
					sb.append('$');
					sc = first + 1;
				}
				else
				{
					String varName = txt.substring(first + 1, second);
					EagleValue value = interpreter.findSymbol(varName);
					if (value == null)
					{
						throw new RuntimeException("Unable to find variable " + varName);
					}
					sb.append(value.forceStringValue());
					sc = second;
				}
			}
		}
		return sb.toString();
	}
}
