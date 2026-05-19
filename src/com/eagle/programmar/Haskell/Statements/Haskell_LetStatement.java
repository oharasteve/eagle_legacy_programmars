// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Haskell_LetStatement extends TokenSequence
{
	public @S(10) Haskell_Keyword LET = new Haskell_Keyword("let");
}
