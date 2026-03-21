// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2026

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalCommentToken;

public class HTML_Code extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public HTML_Code()
	{
		this("");
	}

	public HTML_Code(String text)
	{
		super(text);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		String recLowerCase = rec.toLowerCase();
		int nc = rec.length();
		if (_currentChar + 5 >= nc) return false; // Need room for a sixth character

		if (rec.charAt(_currentChar) != '<') return false;
		if (recLowerCase.charAt(_currentChar + 1) != 'c') return false;
		if (recLowerCase.charAt(_currentChar + 2) != 'o') return false;
		if (recLowerCase.charAt(_currentChar + 3) != 'd') return false;
		if (recLowerCase.charAt(_currentChar + 4) != 'e') return false;
		char nxt = recLowerCase.charAt(_currentChar + 5);
		if (nxt != '>' && nxt != ' ') return false;

		// Is the end on the same line?
		int ec = recLowerCase.indexOf("</code>", _currentChar + 5);
		if (ec >= 0)
		{
			// Yes! Whew!
			foundIt(_currentLine, ec + 3);
			_comment = rec.substring(_currentChar + 4, ec).trim();
			return true;
		}

		// Oh dang ... multi-line comment
		_comment = rec.substring(_currentChar + 5).trim() + "\n";
		int lastLine = _currentLine + 1;
		int numberLines = lines.numberLines();
		while (lastLine < numberLines)
		{
			String next = lines.get(lastLine).toString();
			String nextLowerCase = next.toLowerCase();
			ec = nextLowerCase.indexOf("</code>");
			if (ec >= 0)
			{
				foundIt(lastLine, ec + 6);
				_comment += next.substring(0, ec).trim();
				return true;
			}
			_comment += next.trim() + "\n";
			lastLine++;
		}
		throw new RuntimeException("End of HTML <code>: missing </code>");
	}

	@Override
	public String showString()
	{
		return "code";
	}

	@Override
	public String description()
	{
		return "Preformmated code";
	}
}
