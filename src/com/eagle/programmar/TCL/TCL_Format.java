// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AdditiveEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;

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
		while (sc < nc)
		{
			// Pull in a text string
			int firstBracket = txt.indexOf('[', sc);
			int firstDollar = txt.indexOf('$', sc);
			String var = "";
			
			// Four cases:
			//   I: no more [] or $
			if (firstBracket < 0 && firstDollar < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more [
			}
			
			//  II: just [] or IV: [] before $
			if (firstDollar < 0 || (firstBracket >= 0 && firstBracket < firstDollar))
			{
				int secondBracket = txt.indexOf(']', firstBracket + 1);
				if (secondBracket < 0) throw new RuntimeException("Missing ] in: " + txt);
				var = txt.substring(firstBracket, secondBracket + 1);	// Leave in the brackets
				if (firstBracket > sc) sb.append(txt.substring(sc, firstBracket));
				sc = secondBracket + 1;
			}
			
			// III: just $ or IV: $ before []
			if (firstBracket < 0 || (firstDollar >= 0 && firstDollar < firstBracket))
			{
				int endDollar = firstDollar + 1;
				while (endDollar < nc)
				{
					// Stop on a space or comma or ....
					if (" ,".indexOf(txt.charAt(endDollar)) >= 0) break;
					endDollar++;
				}
				var = txt.substring(firstDollar, endDollar);
				if (firstDollar > sc) sb.append(txt.substring(sc, firstDollar));
				sc = endDollar;
			}

			// Extract a variable name (or expression) and value
			TCL_Expression expr = new TCL_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			sb.append(val);
		}
		return sb.toString();
	}
	
	public static AbstractExpression compile(EagleGenerator generator, String fmt, AbstractToken source)
	{
		AbstractExpression result = null;

		String txt = fmt.replaceAll("\"", "");
		if (txt.indexOf('[') >= 0)
		{
			throw new RuntimeException("Can't handle [] in TCL_Format.compile yet");
		}

		int sc = 0;
		int nc = txt.length();
		// Pull in first text string, if any
		int firstDollar = txt.indexOf('$');
		if (firstDollar >= 0)
		{
			AbstractExpression piece = generator.newLiteralExpression(txt.substring(0, firstDollar), null);
			result = piece;
			sc = firstDollar;
		}
		
		while (sc < nc)
		{
			// Pull in a text string
			firstDollar = txt.indexOf('$', sc);
			if (firstDollar < 0)
			{
				AbstractExpression piece = generator.newLiteralExpression(txt.substring(sc, nc), null);
				if (result == null)
				{
					result = piece;
				}
				else
				{
					result = generator.newAdditiveExpression(null, result, AdditiveEnum.PLUS, piece, null);
				}
				break; // Done -- no more $
			}

			// Pick out the variable name, like $ok
			int endDollar = firstDollar + 1;
			while (endDollar < nc)
			{
				// Stop on a space or comma or what?
				if (" ,.".indexOf(txt.charAt(endDollar)) >= 0) break;
				endDollar++;
			}
			String var = txt.substring(firstDollar + 1, endDollar);
			AbstractExpression varExpr = generator.newVariableExpression(var,
					SubscriptEnum.FIRST_IS_ZERO, null, null);
			// Always wrap in a str() function for now
			AbstractExpression strExpr = generator.newStringFunction(varExpr, null);
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
