// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.programmar.Haskell.Expressions.Haskell_RangeExpression;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_MapFunction extends PrimaryOperator
{
	public @S(10) Haskell_Keyword MAP = new Haskell_Keyword("map");
	public @S(20) Haskell_Identifier_Reference func;
	public @S(30) Haskell_RangeExpression range;
}
