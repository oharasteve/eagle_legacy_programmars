// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;

public class AWK_ContinueStatement extends TokenSequence
{
	public @S(10) @DOC("#Continue-Statement") AWK_Keyword CONTINUE = new AWK_Keyword("continue");
}
