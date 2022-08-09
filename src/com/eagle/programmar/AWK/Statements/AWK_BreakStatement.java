// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;

public class AWK_BreakStatement extends TokenSequence
{
	public @S(10) @DOC("#Break-Statement") AWK_Keyword BREAK = new AWK_Keyword("break");
}
