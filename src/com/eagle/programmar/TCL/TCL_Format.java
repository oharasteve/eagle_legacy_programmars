// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class TCL_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		if (fmt.indexOf('[') < 0 && fmt.indexOf('$') < 0)
		{
			interpreter.pushStr(fmt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		char prev = ' ';
		while (sc < nc)
		{
			char ch = fmt.charAt(sc);

			// Check for an escape before the [] or $
			if (prev != '\\')
			{
				String var = null;
				
				if (ch == '[')
				{
					int secondBracket = fmt.indexOf(']', sc + 1);
					if (secondBracket < 0) throw new RuntimeException("Missing ] in: " + fmt);
					var = fmt.substring(sc, secondBracket + 1); // Leave in the brackets
					sc = secondBracket;
				}
				else if (ch == '$')
				{
					int endDollar = sc + 1;
					while (endDollar < nc)
					{
						// Stop on a space or comma or ....
						if (" ,)".indexOf(fmt.charAt(endDollar)) >= 0) break;
						endDollar++;
					}
					var = fmt.substring(sc, endDollar);
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

	public static AbstractExpression compile(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, String fmt, AbstractToken source)
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
			
			// Check for an escape before the [] or $
			String var = null;
			if (prev != '\\')
			{
				if (ch == '[')
				{
					int secondBracket = fmt.indexOf(']', sc + 1);
					if (secondBracket < 0) throw new RuntimeException("Missing ] in: " + fmt);
					var = fmt.substring(sc, secondBracket + 1); // Leave in the brackets
					sc = secondBracket;
				}
				else if (ch == '$')
				{
					int endDollar = sc + 1;
					while (endDollar < nc)
					{
						// Stop on a space or comma or ....
						if (" ,)".indexOf(fmt.charAt(endDollar)) >= 0) break;
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
