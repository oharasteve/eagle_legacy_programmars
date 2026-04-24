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
		int nc = txt.length();
		int sc = 0;
		while (sc < nc)
		{
			char ch = txt.charAt(sc);
			if (ch == '`' && sc + 1 < nc)
			{
				sc++;
				ch = txt.charAt(sc);
				sb.append(ch);
				continue;
			}
			
			if (ch != '$')
			{
				sb.append(ch);
				sc++;
				continue;
			}

			if (sc + 1 < nc && txt.charAt(sc + 1) == '(')
			{
				// Extract an expression
				int ec = txt.indexOf(")", sc + 2);
				if (ec < 0) throw new RuntimeException("Missing ) in " + txt);
				String var = txt.substring(sc + 2, ec);
				Powershell_Expression expr = new Powershell_Expression();
				if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
				{
					throw new RuntimeException("Unable to parse expression " + var);
				}
				String val = interpreter.getStrValue(expr);
				sb.append(val);

				// Look for the next piece
				sc = ec + 1;
			}
			else
			{
				// Just a variable, like $str
				int ec = sc + 1;
				while (ec < nc)
				{
					ch = txt.charAt(ec);
					if (ch != '_' && !Character.isLetterOrDigit(ch)) break;
					ec++;
				}
				if (sc + 1 == ec)
				{
					// Just a lonely $
					sb.append('$');
					sc = sc + 1;
				}
				else
				{
					String varName = txt.substring(sc + 1, ec);
					if (varName.equalsIgnoreCase("True") || varName.equalsIgnoreCase("False"))
					{
						sb.append("$" + varName);
					}
					else
					{
						EagleValue value = interpreter.findSymbol(varName);
						if (value == null)
						{
							throw new RuntimeException("Unable to find variable " + varName);
						}
						sb.append(value.forceStringValue());
					}
					sc = ec;
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
		char ch = '?';
		StringBuffer piece = new StringBuffer();
		while (sc < nc)
		{
			char prev = ch;
			ch = fmt.charAt(sc);
			
			// Check for an escape (back-tick) first
			if (ch == '`')
			{
				// Toss back-quote ticks
				sc++;
				continue;
			}
			
			String var = null;
			if (prev != '`' && ch == '$')
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
