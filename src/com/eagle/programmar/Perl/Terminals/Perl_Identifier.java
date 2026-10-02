// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public abstract class Perl_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (genericIdentifierWithPrefix(lines, "$#", ALPHAS + "_", ALPHAS + DIGITS + "_")) return true;
		if (genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_", true, false)) return true;
		return underscoresOnly(lines);
	}

	/** __ and _ as names (WordPress's translation functions); the generic identifier wants a letter somewhere. Oct 2026, shane branch. */
	private boolean underscoresOnly(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int n = 0;
		while (_currentChar + n < rec.length() && rec.charAt(_currentChar + n) == '_') n++;
		if (n == 0) return false;
		if (_currentChar + n < rec.length() && (Character.isLetterOrDigit(rec.charAt(_currentChar + n)))) return false;
		setValue("_".repeat(n));
		_endLine = _currentLine;
		_endChar = _currentChar + n - 1;
		return true;
	}
}
