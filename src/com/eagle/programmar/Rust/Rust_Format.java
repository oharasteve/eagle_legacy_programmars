// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

package com.eagle.programmar.Rust;

import com.eagle.core.EagleInterpreter;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Rust_Format
{
	public static String format(EagleInterpreter interpreter, SeparatedList<Rust_Expression, PunctuationComma> argList)
	{
		String fmt = interpreter.getStrValue(argList.first());
		if (fmt.indexOf("{}") < 0) return fmt;
		
		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = argList.getPrimaryCount();
		while (sc < nc)
		{
			// Pull in a text string
			int nxt = fmt.indexOf("{}", sc);
			if (nxt < 0)
			{
				sb.append(fmt.substring(sc, nc));
				break; // Done -- no more {}
			}
			if (nxt > sc)
			{
				sb.append(fmt.substring(sc, nxt));
			}

			// Insert a variable name (or expression)
			index++;
			if (index < numArgs)
			{
				Rust_Expression expr = argList.getPrimaryElement(index);
				String val = interpreter.getStrValue(expr);
				sb.append(val);
			}

			// Look for the next piece
			sc = nxt + 2;
		}
		return sb.toString();
	}
}
