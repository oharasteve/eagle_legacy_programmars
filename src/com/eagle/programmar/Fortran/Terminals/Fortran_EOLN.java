// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2026

package com.eagle.programmar.Fortran.Terminals;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Fortran_EOLN extends TokenChooser
{
	public @CHOICE PunctuationSemicolon XXsemicolon;
	public @CHOICE Fortran_ActualEOLN XXeoln;
}
