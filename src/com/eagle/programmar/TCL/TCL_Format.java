// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class TCL_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		String txt = fmt.replaceAll("\"", "");
		if (txt.indexOf('[') < 0 && txt.indexOf('$') < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		char prev = ' ';
		while (sc < nc)
		{
			char ch = txt.charAt(sc);

			// Check for an escape before the [] or $
			if (prev != '\\')
			{
				String var = null;
				
				if (ch == '[')
				{
					int secondBracket = txt.indexOf(']', sc + 1);
					if (secondBracket < 0) throw new RuntimeException("Missing ] in: " + txt);
					var = txt.substring(sc, secondBracket + 1); // Leave in the brackets
					sc = secondBracket;
				}
				else if (ch == '$')
				{
					int endDollar = sc + 1;
					while (endDollar < nc)
					{
						// Stop on a space or comma or ....
						if (" ,)".indexOf(txt.charAt(endDollar)) >= 0) break;
						endDollar++;
					}
					var = txt.substring(sc, endDollar);
					sc = endDollar - 1;
				}
				else
				{
					sb.append(ch);
				}
	
				// Extract a variable name (or expression) and value
				if (var != null)
				{
					TCL_Expression expr = new TCL_Expression();
					if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
					{
						throw new RuntimeException("Unable to parse expression " + var);
					}
					String val = interpreter.getStrValue(expr);
					sb.append(val);
				}
			}
			
			sc++;
			prev = ch;
		}
		return sb.toString();
	}

	public static AbstractExpression compile(EagleGenerator generator, String fmt, AbstractToken source)
	{
		AbstractExpression result = null;

		String txt = fmt.replaceAll("\"", "");
		int nc = txt.length();
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		if (txt.indexOf('[') >= 0)
		{
			throw new RuntimeException("Can't handle [] in TCL_Format.compile yet");
		}

		int sc = 0;
		while (sc < nc)
		{
			// Pull in a text string
			int nextDollar = txt.indexOf('$', sc);
			int ec = nextDollar;
			if (nextDollar < 0)
			{
				ec = nc; // No more $, go all the way to the end
			}

			if (ec > sc)
			{
				// Grab next literal piece
				AbstractExpression piece1 = generator.newLiteralExpression(txt.substring(sc, ec), null);
				if (result == null)
				{
					result = piece1;
				}
				else
				{
					result = generator.newAdditiveExpression(null, result, AdditiveEnum.PLUS, piece1, null);
				}
			}

			if (nextDollar < 0)
			{
				break; // Done -- no more $
			}

			// Pick out the variable name, like $ok
			int endDollar = nextDollar + 1;
			while (endDollar < nc)
			{
				// Stop on a space or comma or what?
				if (" ,.".indexOf(txt.charAt(endDollar)) >= 0) break;
				endDollar++;
			}
			String var = txt.substring(nextDollar + 1, endDollar);
			AbstractExpression varExpr = generator.newVariableExpression(var,
					SubscriptEnum.FIRST_IS_ZERO, null, null);
			// Always wrap in a str() function for now
			AbstractExpression strExpr = generator.newStringFunction(null, varExpr, null);
			if (result == null)
			{
				result = varExpr;
			}
			else
			{
				result = generator.newAdditiveExpression(null, result, AdditiveEnum.PLUS, strExpr, null);
			}
			sc = endDollar;
		}

		return result;
	}
}
