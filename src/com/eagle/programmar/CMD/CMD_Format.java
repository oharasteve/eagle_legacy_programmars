// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2024

package com.eagle.programmar.CMD;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;

public class CMD_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		String txt = fmt.replaceAll("\"", "");

		if (txt.indexOf('%') >= 0)
		{
			StringBuffer sb = new StringBuffer();
			int sc = 0;
			int nc = txt.length();
			while (sc < nc)
			{
				// Pull in a text string
				int firstPct = txt.indexOf('%', sc);
				if (firstPct < 0)
				{
					sb.append(txt.substring(sc, nc));
					break; // Done -- no more %
				}

				// Copy over plain text between %vars%
				if (firstPct > sc)
				{
					sb.append(txt.substring(sc, firstPct));
				}

				// Check for %%I
				if (firstPct + 2 < nc)
				{
					if (txt.charAt(firstPct + 1) == '%')
					{
						if (Character.isLetter(txt.charAt(firstPct + 2)))
						{
							String var = txt.substring(firstPct, firstPct + 3);
							EagleValue val = interpreter.findSymbol(var);
							sb.append(val.forceStringValue());
							sc = firstPct + 3;
							continue;
						}
					}
				}

				// Check for %~2
				if (firstPct + 2 < nc)
				{
					if (txt.charAt(firstPct + 1) == '~')
					{
						if (Character.isDigit(txt.charAt(firstPct + 2)))
						{
							String var = txt.substring(firstPct, firstPct + 3);
							EagleValue val = interpreter.findSymbol(var);
							sb.append(val.forceStringValue());
							sc = firstPct + 3;
							continue;
						}
					}
				}

				// Extract a variable name (or expression) and value, like %abc%
				int secondPct = txt.indexOf('%', firstPct + 1);
				if (secondPct < 0) throw new RuntimeException("Missing % in " + txt);
				String var = txt.substring(firstPct + 1, secondPct);
				EagleValue val = interpreter.findSymbol(var);
				if (val == null)
				{
					throw new RuntimeException("Unable to find variable '" + var + "'");
				}
				sb.append(val.forceStringValue());

				// Look for the next piece
				sc = secondPct + 1;
			}
			txt = sb.toString();
		}

		if (txt.indexOf('!') >= 0)
		{
			StringBuffer sb = new StringBuffer();
			int sc = 0;
			int nc = txt.length();
			while (sc < nc)
			{
				// Pull in a text string
				int firstBang = txt.indexOf('!', sc);
				if (firstBang < 0)
				{
					sb.append(txt.substring(sc, nc));
					break; // Done -- no more !
				}

				// Copy over plain text between !vars!
				if (firstBang > sc)
				{
					sb.append(txt.substring(sc, firstBang));
				}

				// Extract a variable name (or expression) and value, like !abc!
				int secondBang = txt.indexOf('!', firstBang + 1);
				if (secondBang < 0) throw new RuntimeException("Missing ! in " + txt);
				String name = txt.substring(firstBang + 1, secondBang);

				int colon = name.indexOf(":~");
				int comma = name.indexOf(',');
				int subSC = 0;
				int subNC = 0;
				if (colon > 0 && comma > colon)
				{
					subSC = Integer.parseInt(name.substring(colon + 2, comma));
					subNC = Integer.parseInt(name.substring(comma + 1));
					name = name.substring(0, colon);
				}

				EagleValue val = interpreter.findSymbol(name);
				String piece = val.forceStringValue();

				if (colon > 0)
				{
					// CMD allows access beyond the end.
					// Java does not. We have to validate sc and ec
					int pieceNC = piece.length();
					if (subSC >= pieceNC)
					{
						piece = "";
					}
					else
					{
						int subEC = subSC + subNC;
						if (subEC > pieceNC) subEC = pieceNC;
						piece = piece.substring(subSC, subEC);
					}
				}

				sb.append(piece);

				// Look for the next piece
				sc = secondBang + 1;
			}
			txt = sb.toString();
		}

		return txt;
	}
}
