// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_ContinueStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#Continue-Statement") AWK_Keyword CONTINUE = new AWK_Keyword("continue");
}
