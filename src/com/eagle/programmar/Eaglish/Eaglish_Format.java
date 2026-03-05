// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Format
{
	public static String format(EagleInterpreter interpreter, String txt)
	{
		if (txt.indexOf('^') < 0 && txt.indexOf('\\') < 0)
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

			if (prev == '\\')
			{
				sb.append(ch);	// This character, no matter what it is
			}
			else if (ch == '\\')
			{
				// Don't do anything with this, it escapes the next character
			}
			else if (ch == '^')
			{
				// Extract a variable name (or expression) and value
				int second = txt.indexOf('^', sc + 1);
				if (second < 0) throw new RuntimeException("Missing ^ in " + txt);
				String var = txt.substring(sc + 1, second);
				Eaglish_Expression expr = new Eaglish_Expression();
				if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
				{
					throw new RuntimeException("Unable to parse expression " + var);
				}
				String val = interpreter.getStrValue(expr);
				sb.append(val);
	
				// Get past the second ^
				sc = second;
			}
			else
			{
				sb.append(ch);	// Just a plain old character, save it
			}
		
			sc++;
			prev = ch;
		}
		return sb.toString();
	}

	public static AbstractExpression compile(EagleTransformer transformer,
			EagleGenerator generator, String fmt, AbstractToken source)
	{
		AbstractExpression result = null;

		String txt = fmt.replaceAll("\"", "");
		int nc = txt.length();
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		int sc = 0;
		while (sc < nc)
		{
			// Pull in a text string
			int nextInsertion = txt.indexOf("^", sc);
			int ec = nextInsertion;
			if (nextInsertion < 0)
			{
				ec = nc; // No more ^, go all the way to the end
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
				break; // Done -- no more ^
			}

			// Pick out the variable name, like ^ok^
			int endInsertion = txt.indexOf("^", nextInsertion + 1);
			if (endInsertion < 0)
			{
				throw new RuntimeException("Missing second ^ following ^");
			}
			String text = txt.substring(nextInsertion + 1, endInsertion);
			Eaglish_Expression expr = new Eaglish_Expression();
			if (!generator._parser.parseLine(text, generator._parser._parser.getLanguage(), expr))
			{
				throw new RuntimeException("Unable to parse expression " + expr);
			}
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			// Always wrap in a str() function for now
			AbstractExpression strExpr = generator.newStringFunction(null, newExpr, null);
			if (result == null)
			{
				result = strExpr;
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
