// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_UnlinesFunction extends PrimaryOperator
{
	public @S(10) Haskell_Keyword UNLINES = new Haskell_Keyword("unlines");
	public @S(20) Haskell_Expression expr;
}
