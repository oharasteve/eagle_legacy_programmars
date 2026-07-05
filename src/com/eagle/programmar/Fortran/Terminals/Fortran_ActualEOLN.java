// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2026

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.terminals.TerminalEndOfLine;

public class Fortran_ActualEOLN extends TerminalEndOfLine
{
	public @CHOICE PunctuationSemicolon XXsemicolon;
	public @CHOICE TerminalEndOfLine XXeoln;
}
