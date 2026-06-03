// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Haskell_DoStatement extends TokenSequence
{
	public @S(10) Haskell_Keyword DO = new Haskell_Keyword("do");
	public @S(20) Haskell_EndOfLine eoln;
	public @S(30) @PYDENT Haskell_StatementBlock doBlock;
}
