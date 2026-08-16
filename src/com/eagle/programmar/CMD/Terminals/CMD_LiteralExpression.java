// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2024

package com.eagle.programmar.CMD.Terminals;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.tokens.terminals.TerminalLiteralExpression;

public class CMD_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, String txt)
	{
		String fmt = txt;
		if (txt.startsWith("\"") && txt.endsWith("\""))
		{
			fmt = txt.substring(1, txt.length() - 2);
		}
		
		ArgumentsMetrics metrics = null;
		if (fmt.indexOf('%') >= 0)
		{
			ArrayList<LiteralPiece> pieces = parseCmdPercent(fmt, '%', '~');
			fmt = evaluateLiteral(interpreter, metrics, CMD_Expression.class, pieces);
		}
		if (fmt.indexOf('!') >= 0)
		{
			ArrayList<LiteralPiece> pieces = parseCmdBang(fmt, '!', '~');
			fmt = evaluateLiteral(interpreter, metrics, CMD_Expression.class, pieces);
		}
		return fmt;
	}

	private static ArrayList<LiteralPiece> parseCmdPercent(String txt, char percentCh, char tildeCh)
	{
		ArrayList<LiteralPiece> pieces = new ArrayList<LiteralPiece>();
	
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int firstPct = txt.indexOf(percentCh, sc);
			if (firstPct < 0)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, nc)));
				break; // Done -- no more %
			}

			// Copy over plain text between %vars%
			if (firstPct > sc)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, firstPct)));
			}

			// Check for %%I
			if (firstPct + 2 < nc)
			{
				if (txt.charAt(firstPct + 1) == percentCh)
				{
					if (Character.isLetter(txt.charAt(firstPct + 2)))
					{
						String var = txt.substring(firstPct, firstPct + 3);
						pieces.add(LiteralPiece.addVar(var));
						sc = firstPct + 3;
						continue;
					}
				}
			}

			// Check for %~2
			if (firstPct + 2 < nc)
			{
				if (txt.charAt(firstPct + 1) == tildeCh)
				{
					if (Character.isDigit(txt.charAt(firstPct + 2)))
					{
						String var = txt.substring(firstPct, firstPct + 3);
						pieces.add(LiteralPiece.addVar(var));
						sc = firstPct + 3;
						continue;
					}
				}
			}

			// Extract a variable name (or expression) and value, like %abc%
			int secondPct = txt.indexOf(percentCh, firstPct + 1);
			if (secondPct < 0) throw new RuntimeException("Missing " + percentCh + " in " + txt);
			String var = txt.substring(firstPct + 1, secondPct);
			pieces.add(LiteralPiece.addVar(var));

			// Look for the next piece
			sc = secondPct + 1;
		}

		return pieces;
	}
	
	public static ArrayList<LiteralPiece> parseCmdBang(String txt, char bangCh, char tildeCh)
	{
		ArrayList<LiteralPiece> pieces = new ArrayList<LiteralPiece>();

		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int firstBang = txt.indexOf(bangCh, sc);
			if (firstBang < 0)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, nc)));
				break; // Done -- no more !
			}

			// Copy over plain text between !vars!
			if (firstBang > sc)
			{
				pieces.add(LiteralPiece.addText(txt.substring(sc, firstBang)));
			}

			// Extract a variable name (or expression) and value, like !abc!
			int secondBang = txt.indexOf(bangCh, firstBang + 1);
			if (secondBang < 0) throw new RuntimeException("Missing ! in " + txt);
			String varName = txt.substring(firstBang + 1, secondBang);

			int colon = varName.indexOf(":" + tildeCh);
			if (colon > 0)
			{
				int comma = varName.indexOf(',');
				if (comma > colon)
				{
					String subSc = varName.substring(colon + 2, comma);
					String subNc = varName.substring(comma + 1);
					varName = varName.substring(0, colon);
					pieces.add(LiteralPiece.addVarScNc(varName, subSc, subNc));
				}
				else
				{
					String subSc = varName.substring(colon + 2);
					varName = varName.substring(0, colon);
					pieces.add(LiteralPiece.addVarSc(varName, subSc));
				}
			}
			else
			{
				pieces.add(LiteralPiece.addVar(varName));
			}

			// Look for the next piece
			sc = secondBang + 1;
		}
	
		return pieces;
	}
}
