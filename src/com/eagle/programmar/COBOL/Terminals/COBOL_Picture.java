// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalPictureToken;

public class COBOL_Picture extends TerminalPictureToken
{
	private static final String BRITISH_POUND = "\uFFFD"; // 65533 in decimal. Looks like a cursive L
	private static final String FIRST = "9ZXSVBP+-*$" + BRITISH_POUND;
	private static final String REST = FIRST + "DCR()012345678/,";

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch1 = Character.toUpperCase(rec.charAt(_currentChar));
		if (FIRST.indexOf(ch1) >= 0)
		{
			_endChar = _currentChar + 1;
			int countLeftParen = 0;
			int countRightParen = 0;
			while (_endChar < recLen)
			{
				char ch2 = Character.toUpperCase(rec.charAt(_endChar));
				if (ch2 == '(')
					countLeftParen++;
				else if (ch2 == ')') countRightParen++;

				if (REST.indexOf(ch2) < 0
						&& !(ch2 == '.' && _endChar + 1 < recLen && Character.isDigit(rec.charAt(_endChar + 1))))
				{
					break;
				}

				_endChar++;
			}

			if (countLeftParen != countRightParen) return false;
			foundIt(_currentLine, _endChar - 1);
			_pic = rec.substring(_currentChar, _endChar + 1);
			return true;
		}
		return false;
	}

	@Override
	public String description()
	{
		return "A COBOL PICTURE, such 99V99 or X(10)";
	}
}
