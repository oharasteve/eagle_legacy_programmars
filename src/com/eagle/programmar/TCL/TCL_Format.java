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

		String txt = fmt;
		int nc = fmt.length();
		if (fmt.startsWith("\"") && fmt.endsWith("\"") && nc > 1)
		{
			// Strip quotes
			txt = fmt.substring(1, nc-1);
			nc -= 2;
		}
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		int sc = 0;
		char prev = ' ';
		StringBuffer piece = new StringBuffer();
		while (sc < nc)
		{
			char ch = txt.charAt(sc);
			
			// Check for an escape before the [] or $
			String var = null;
			if (prev != '\\')
			{
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
					var = txt.substring(sc+1, endDollar);	// Skip the leading $
					sc = endDollar - 1;
				}
			}
				
			if (var != null)
			{
				if (piece.length() > 0)
				{
					AbstractExpression litExpr = generator.newLiteralExpression(piece.toString(), null);
					if (result == null)
					{
						result = litExpr;
					}
					else
					{
						result = generator.newAdditiveExpression(null, result, AdditiveEnum.PLUS, litExpr, null);
					}
					piece = new StringBuffer();		// Start over
				}

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
			}
			else if (ch != '\\' || prev == '\\')
			{
				piece.append(ch);
			}
			
			sc++;
			prev = ch;
		}
		
		if (piece.length() > 0)
		{
			AbstractExpression litExpr = generator.newLiteralExpression(piece.toString(), null);
			if (result == null)
			{
				result = litExpr;
			}
			else
			{
				result = generator.newAdditiveExpression(null, result, AdditiveEnum.PLUS, litExpr, null);
			}
		}

		return result;
	}
}
