// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apri 23, 2026

package com.eagle.programmar.Powershell;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Powershell_Format
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
		char prev = '?';
		int i = 0;
		while (i < nc)
		{
			char ch = txt.charAt(i);
			if (ch != '$' || prev == '`')
			{
				i++;
				prev = ch;
				continue;
			}

			// Was there any stuff before the $ ??
			if (sc < i)
			{
				// Yep, add text
				sb.append(txt.substring(sc, i));
			}
			
			
			
			
			
			// HERE I AM
			
			
			
			
			
			
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
			if (first + 1 < nc && txt.charAt(first + 1) == '(')
			{
				// Extract an expression
				int second = txt.indexOf(")", first + 2);
				if (second < 0) throw new RuntimeException("Missing ) in " + txt);
				String var = txt.substring(first + 2, second);
				Powershell_Expression expr = new Powershell_Expression();
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
					ch = txt.charAt(second);
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

	public static AbstractExpression compile(
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			String fmt, AbstractToken source)
	{
		AbstractExpression result = null;

		int nc = fmt.length();
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		int sc = 0;
		char prev = ' ';
		StringBuffer piece = new StringBuffer();
		while (sc < nc)
		{
			char ch = fmt.charAt(sc);
			
			// Check for an escape (back-tick) before the $
			String var = null;
			if (prev != '`')
			{
				if (ch == '$')
				{
					int endDollar = sc + 1;
					while (endDollar < nc)
					{
						// Stop on a non-alphanumeric
						char nxt = fmt.charAt(endDollar);
						if (nxt != '_' && !Character.isLetterOrDigit(nxt)) break;
						endDollar++;
					}
					var = fmt.substring(sc+1, endDollar);	// Skip the leading $
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
