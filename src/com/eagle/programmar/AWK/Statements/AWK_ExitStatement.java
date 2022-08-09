// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 9, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;

public class AWK_ExitStatement extends TokenSequence
{
	public @S(10) @DOC("#Exit-Statement") AWK_Keyword EXIT = new AWK_Keyword("exit");
}
