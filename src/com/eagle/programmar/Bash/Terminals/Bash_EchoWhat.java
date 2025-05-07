// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 18, 2024

package com.eagle.programmar.Bash.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Bash_EchoWhat extends TerminalLiteralToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int endChar = _currentChar;
		if (endChar >= recLen) return false;

		boolean inQuotes1 = false;
		boolean inQuotes2 = false;
		while (endChar < recLen)
		{
			char ch = rec.charAt(endChar);
			if (!inQuotes2 && ch == '\'') inQuotes1 = !inQuotes1;
			if (!inQuotes1 && ch == '"') inQuotes2 = !inQuotes2;

			if (!inQuotes1 && !inQuotes2)
			{
				if (ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == ';')
				{
					endChar--;
					break;
				}
			}
			endChar++;
		}

		foundIt(_currentLine, endChar);
		_txt += rec.substring(_currentChar, endChar);
		return true;
	}
	
	@Override
	public String description()
	{
		return "rest of line";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = _txt.replaceAll("\"", "");
		if (txt.indexOf("$((") < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf("$((", sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more $((
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}

			// Extract a variable name (or expression) and value
			int second = txt.indexOf("))", first + 3);
			while (second + 2 < nc && txt.charAt(second + 2) == ')')
			{
				second++;
			} // In case there is something like $(((1+2)))
			if (second < 0) throw new RuntimeException("Missing )) in " + txt);
			String var = txt.substring(first + 3, second);
			Bash_Expression expr = new Bash_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			sb.append(val);

			// Look for the next piece
			sc = second + 2;
		}
		interpreter.pushStr(sb.toString());
	}
}
