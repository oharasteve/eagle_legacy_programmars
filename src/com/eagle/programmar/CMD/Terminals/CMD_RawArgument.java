// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.CMD.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleSyntax;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CMD_RawArgument extends TerminalLiteralToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		if (ch == ':' || ch == '@' || ch == '-' || ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == ')')
			return false;

		int offset = 0;
		char quote;
		if (ch == '"' || ch == '\'')
		{
			quote = ch;

			_endChar = _currentChar;
			while (true)
			{
				_endChar++;
				if (_endChar >= recLen) break;
				ch = rec.charAt(_endChar);
				if (ch == quote) break;
			}
			_txt = rec.substring(_currentChar + 1, _endChar);
		}
		else if (ch == '/')
		{
			// Allow /%%x
			if (_currentChar + 3 >= recLen) return false;
			if (rec.charAt(_currentChar + 1) != '%') return false;
			if (rec.charAt(_currentChar + 2) != '%') return false;
			ch = rec.charAt(_currentChar + 3);
			if (!Character.isLetter(ch)) return false;
			_endChar = _currentChar + 3;
		}
		else
		{
			// Plain argument, no quotes
			_endChar = _currentChar;
			while (true)
			{
				_endChar++;
				if (_endChar >= recLen) break;
				ch = rec.charAt(_endChar);
				if (ch == ' ' || ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == '(' || ch == ')') break;

				// Don't allow == in the middle of an argument
				if (ch == '=')
				{
					if (_endChar + 1 < recLen)
					{
						if (rec.charAt(_endChar + 1) == '=') break;
					}
				}
			}
			_txt = rec.substring(_currentChar, _endChar);

			// Make sure a generic argument is not a program name like SET or REM or
			// whatever
			String word = _txt.toUpperCase();
			int dot = word.indexOf('.');
			if (dot > 0)
			{
				word = word.substring(0, dot);
			}
			EagleSyntax syntax = getSyntax();
			if (syntax.isReserved(word)) return false;
			offset = 1;
		}

		foundIt(_currentLine, _endChar - offset);
		return true;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = _txt.replaceAll("\"", "");
		if (txt.indexOf('%') < 0)
		{
			interpreter.pushStr(txt);
		}

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = txt.length();
		while (sc < nc)
		{
			// Pull in a text string
			int first = txt.indexOf('%', sc);
			if (first < 0)
			{
				sb.append(txt.substring(sc, nc));
				break; // Done -- no more %
			}
			if (first > sc)
			{
				sb.append(txt.substring(sc, first));
			}

			// Extract a variable name (or expression) and value
			int second = txt.indexOf('%', first + 1);
			if (second < 0) throw new RuntimeException("Missing % in " + txt);
			String var = txt.substring(first + 1, second);
			CMD_Expression expr = new CMD_Expression();
			if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
			{
				throw new RuntimeException("Unable to parse expression " + var);
			}
			String val = interpreter.getStrValue(expr);
			sb.append(val);

			// Look for the next piece
			sc = second + 1;
		}
		interpreter.pushStr(sb.toString());
	}
}
