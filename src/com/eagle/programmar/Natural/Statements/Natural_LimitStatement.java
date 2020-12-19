// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 7, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.TokenSequence;

public class Natural_LimitStatement extends TokenSequence
{
	public @S(10) @DOC("sm/limit.htm") Natural_Keyword LIMIT = new Natural_Keyword("LIMIT");
	public @S(20) Natural_Number count;
}
