// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Eaglish_Literal extends TerminalLiteralToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"", true, '\\', false, false);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = _txt.replaceAll("\"", "");
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
		interpreter.pushStr(sb.toString());
	}
}
