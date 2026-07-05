// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformer;

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

	public static AbstractExpression compile(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			String fmt, AbstractToken source)
	{
		if (fmt.indexOf('[') < 0 && fmt.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(fmt, source);
		}
		
		int nc = fmt.length();
		if (nc == 0)
		{
			return generator.newLiteralExpression("", null);
		}

		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
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
					pieces.add(litExpr);
					piece = new StringBuffer();
				}

				TCL_Expression expr = new TCL_Expression();
				if (!generator._parser.parseLine(var, generator._parser._parser.getLanguage(), expr))
				{
					throw new RuntimeException("Unable to parse expression " + expr);
				}
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				pieces.add(newExpr);
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
			pieces.add(litExpr);
		}

		return generator.newConcatExpression(pieces, source);
	}
}
