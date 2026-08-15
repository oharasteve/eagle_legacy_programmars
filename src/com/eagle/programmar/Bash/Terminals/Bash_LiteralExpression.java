// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Bash.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Bash_LiteralExpression extends TerminalLiteralExpression
{
	public static ArrayList<LiteralPiece> parseBashDollar(String fmt)
	{
		String txt = fmt;
		if (txt.startsWith("\""))
		{
			// Toss leading and trailing quotes, if present
			txt = txt.substring(1, txt.length()-1);
		}
		
		// Change \" to "
		txt = txt.replaceAll("\\\\", "\\");
		
		ArrayList<LiteralPiece> pieces = new ArrayList<LiteralPiece>();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf('$', sc);
			if (first < 0)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, nc)));
				break; // Done -- no more $
			}
			if (first > sc)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, first)));
			}
			if (first + 1 < nc && txt.charAt(first + 1) == '{')
			{
				// Extract an expression
				int second = txt.indexOf("}", first + 2);
				if (second < 0) throw new RuntimeException("Missing } in " + txt);
				String var = txt.substring(first + 2, second);
				pieces.add(LiteralPiece.addVar(var));

				// Look for the next piece
				sc = second + 1;
			}
			else
			{
				// Just a variable, like $str
				int second = first + 1;
				while (second < nc)
				{
					char ch = txt.charAt(second);
					if (!Character.isLetterOrDigit(ch)) break;
					second++;
				}
				if (first + 1 == second)
				{
					// Just a lonely $
					pieces.add(LiteralPiece.addText("$"));
					sc = first + 1;
				}
				else
				{
					String var = txt.substring(first + 1, second);
					pieces.add(LiteralPiece.addVar(var));
					sc = second;
				}
			}
		}
		return pieces;
	}
	
	public static String interpret(EagleInterpreter interpreter, String fmt,
			ArgumentsMetrics metrics)
	{
		if (fmt.startsWith("'"))
		{
			return fmt.substring(1, fmt.length() - 2);
		}
		if (fmt.indexOf('$') < 0)
		{
			return fmt;
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt);
		return evaluateLiteral(interpreter, metrics, Bash_Expression.class, pieces);
	}
	
	public static AbstractExpression UNUSED_UNTESTED_transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		TokenList<Bash_Expression> argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		Bash_Expression expr = argList.first();
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Bash_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Bash_Literal str = (Bash_Literal) which;
		String fmt = str.getValue();
		if (fmt.startsWith("'"))
		{
			return generator.newLiteralExpression(fmt.substring(1, fmt.length() - 2), source);
		}
		if (fmt.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(fmt, source);
		}
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 2);
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Bash_Expression.class, pieces, source);
		return result;
	}
}
