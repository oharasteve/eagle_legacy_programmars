// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Terminals.Python_BackQuote;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Python_BackQuotes extends PrimaryOperator
{
	// These are obsolete as of Python 3.
	public @S(10) @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
}
