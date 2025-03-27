// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public class CMD_PctPctVariable extends TerminalIdentifierToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int endChar = _currentChar + 1;
		if (endChar >= recLen) return false;
		if (rec.charAt(_currentChar) != '%') return false;
		char nextCh = rec.charAt(_currentChar + 1);
		if (nextCh == '%')
		{
			// More stuff goes here, starting with ~
			endChar = _currentChar + 2;
			if (endChar >= recLen) return false;
			if (!Character.isLetter(rec.charAt(endChar))) return false;
		}
		else if (nextCh == '~')
		{
			endChar = _currentChar + 2;
			if (endChar >= recLen) return false;
			if (!Character.isDigit(rec.charAt(endChar))) return false;
		}
		else if (!Character.isDigit(nextCh))
		{
			return false;
		}
		
		_id = rec.substring(_currentChar, endChar + 1);
		foundIt(_currentLine, endChar);
		return true;
	}

	@Override
	public String showString()
	{
		return "Percent Identifier";
	}

	@Override
	public String description()
	{
		return "An identifier like %%A or %~2";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(this.getValue());
		interpreter.pushEagleValue(value);
	}
}
