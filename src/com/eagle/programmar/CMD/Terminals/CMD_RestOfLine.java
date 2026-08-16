// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TokenRestOfLine;

public class CMD_RestOfLine extends TokenRestOfLine implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		_currentLine = lines.getCurrentLine();
		_currentChar = lines.getCurrentChar();
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();

		// Skip leading spaces
		while (_currentChar < recLen && rec.charAt(_currentChar) == ' ') _currentChar++;

		int rParen = rec.indexOf(')', _currentChar);
		if (rParen > 0)
		{
			int sc = _currentChar;
			int parens = 0;		// +1 for ) and -1 for (
			while (sc < recLen)
			{
				char ch = rec.charAt(sc);
				if (ch == '(')
				{
					parens--;
				}
				else if (ch == ')')
				{
					parens++;
					if (parens > 0)
					{
						recLen = sc - 1;
						break;
					}
				}
				else if (parens == 0 && ch == '&')
				{
					recLen = sc - 1;
					break;
				}
				sc++;
			}
		}
		
		// No ), go all the way to the end
		foundIt(_currentLine, recLen);
		_txt = rec.substring(_currentChar, recLen);
		return true;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String formatted = CMD_LiteralExpression.interpret(interpreter, this.getValue());
		interpreter.pushStr(formatted);
	}
}
