// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Delphi;

import java.util.ArrayList;

import com.eagle.math.EagleValue;

public class Delphi_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(String fmt, ArrayList<EagleValue> values)
	{
		if (fmt.indexOf('%') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = values.size();
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
			if (index < numArgs)
			{
				sb.append(values.get(index));
			}
			index++;

			// Look for the next piece
			sc = pct + 2;
		}
		return sb.toString();
	}
}
