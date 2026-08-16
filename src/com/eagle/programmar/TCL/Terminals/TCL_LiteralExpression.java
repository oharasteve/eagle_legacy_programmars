// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_LiteralExpression extends TerminalLiteralExpression
{
	public static ArrayList<LiteralPiece> parseBracketDollar(String fmt)
	{
		ArrayList<LiteralPiece> pieces = new ArrayList<LiteralPiece>();
		int sc = 0;
		int nc = fmt.length();
		char prevCh = ' ';
		StringBuffer piece = new StringBuffer();
		while (sc < nc)
		{
			char ch = fmt.charAt(sc);

			// Check for an escape before the [] or $
			if (prevCh == '\\')
			{
				piece.append(ch);	// Take this character, no matter what it is
			}
			else if (ch == '\\')
			{
				// Don't do anything with this, it escapes the next character
			}
			else if (ch == '[')
			{
				if (piece.length() > 0)
				{
					pieces.add(LiteralPiece.addText(piece.toString()));
					piece.setLength(0);
				}
				
				int secondBracket = fmt.indexOf(']', sc + 1);
				if (secondBracket < 0) throw new RuntimeException("Missing ] in: " + fmt);
				String var1 = fmt.substring(sc, secondBracket + 1); // Leave in the brackets
				pieces.add(LiteralPiece.addVar(var1));
				sc = secondBracket;
			}
			else if (ch == '$')
			{
				if (piece.length() > 0)
				{
					pieces.add(LiteralPiece.addText(piece.toString()));
					piece.setLength(0);
				}
				
				int endDollar = sc + 1;
				while (endDollar < nc)
				{
					// Stop on a space or comma or ....
					if (" ,)".indexOf(fmt.charAt(endDollar)) >= 0) break;
					endDollar++;
				}
				String var2 = fmt.substring(sc, endDollar);
				pieces.add(LiteralPiece.addVar(var2));
				sc = endDollar - 1;
			}
			else
			{
				piece.append(ch);
			}
	
			sc++;
			prevCh = ch;
		}
		
		if (piece.length() > 0)
		{
			pieces.add(LiteralPiece.addText(piece.toString()));
			piece.setLength(0);
		}
	
		return pieces;
	}

	public static String interpret(EagleInterpreter interpreter, String fmt,
			ArgumentsMetrics metrics)
	{
		if (fmt.indexOf('[') < 0 && fmt.indexOf('$') < 0)
		{
			return fmt;
		}

		ArrayList<LiteralPiece> pieces = parseBracketDollar(fmt);
		return evaluateLiteral(interpreter, metrics, TCL_Expression.class, pieces);
	}

	public static AbstractExpression transform(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			String fmt, AbstractToken source)
	{
		if (fmt.indexOf('[') < 0 && fmt.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(fmt, source);
		}
		
		ArrayList<TypeEnum> metrics = null;
		ArrayList<LiteralPiece> pieces = parseBracketDollar(fmt);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				TCL_Expression.class, pieces, source);
		return result;
	}
}
