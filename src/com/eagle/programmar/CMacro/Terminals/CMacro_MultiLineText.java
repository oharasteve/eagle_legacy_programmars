// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2015

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CMacro_MultiLineText extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) != FOUND.GOOD) return false;
		StringBuffer text = new StringBuffer();
		int endLine = _currentLine;
		EagleLineReader firstRec = lines.get(_currentLine);
		int recLen = firstRec.length();
		if (recLen < _currentChar) return false;

		boolean inComment = false;
		while (endLine < lines.numberLines())
		{
			EagleLineReader rec = lines.get(endLine);
			recLen = rec.length();
			endLine++;

			// Don't allow lines that start with a #
			if (!inComment && rec.trim().startsWith("#"))
			{
				endLine--;
				if (endLine == _currentLine) return false;
				break;
			}

			boolean inQuotes = false;
			char prevch = '?';	// Anything that won't match
			for (int i = 0; i < recLen - 1; i++) // -1 so we don't run off the end
			{
				char ch = rec.charAt(i);
				if (ch == '\\' && prevch == '\\')
				{
					ch = '?';	// Anything that won't match
				}
				else
				{
					char nextch = rec.charAt(i + 1);
					if (ch == '"' && prevch != '\\') inQuotes = !inQuotes;
					if (!inQuotes)
					{
						if (ch == '/' && nextch == '*') inComment = true;
						if (ch == '*' && nextch == '/') inComment = false;
						if (!inComment && (ch == '/' && nextch == '/')) break;
					}
				}
				prevch = ch;
			}

			if (text.length() > 0) text.append('\n');
			text.append(rec);
		}
		if (text.length() == 0) return false;

		_txt = text.toString();
		foundIt(endLine, -1);
		return true;
	}

	@Override
	public String description()
	{
		return "multiline text";
	}
}