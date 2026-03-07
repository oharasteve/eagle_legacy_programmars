// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2024

package com.eagle.programmar.Ruby;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Ruby_Format
{
	public static String format(EagleInterpreter interpreter, String fmt)
	{
		int nc = fmt.length();
		String txt = fmt.substring(1, nc-1).replaceAll("\\\\\"", "\"");
		if (txt.indexOf("#{") < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf("#{", sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more #{
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}

			// Extract a variable name (or expression) and value
			int second = txt.indexOf('}', first + 2);
			if (second < 0) throw new RuntimeException("Missing } in " + txt);
			String var = txt.substring(first + 2, second);
			// System.out.println("***** piece=" + var + " ******");
			Ruby_Expression expr = new Ruby_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			// System.out.println("***** val=" + val + " ******");
			sb.append(val);

			// Look for the next piece
			sc = second + 1;
		}
		return sb.toString();
	}

	public static AbstractExpression compile(EagleGenerator generator, String fmt, AbstractToken source)
	{
		AbstractExpression result = null;

		int nc = fmt.length();
		String txt = fmt.substring(1, nc-1).replaceAll("\\\\\"", "\"");
		nc = txt.length();
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		int sc = 0;
		while (sc < nc)
		{
			// Pull in a text string
			int nextInsertion = txt.indexOf("#{", sc);
			int ec = nextInsertion;
			if (nextInsertion < 0)
			{
				ec = nc; // No more #{}, go all the way to the end
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

			if (nextInsertion < 0)
			{
				break; // Done -- no more #{}
			}

			// Pick out the variable name, like #{ok}
			int endInsertion = txt.indexOf("}", nextInsertion + 2);
			if (endInsertion < 0)
			{
				throw new RuntimeException("Missing } following #{");
			}
			String var = txt.substring(nextInsertion + 2, endInsertion);
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
			sc = endInsertion + 1;
		}

		return result;
	}
}
