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

		int rParen = rec.indexOf(')', _currentChar);
		if (rParen > 0)
		{
			// But don't consider ^)
			char prev = rec.charAt(rParen - 1);
			if (prev != '^')
			{
				// Treat ) as end-of-line
				recLen = rParen - 1;
			}
		}
		
		// No ), go all the way to the end
		while (_currentChar < recLen && rec.charAt(_currentChar) == ' ') _currentChar++;
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
