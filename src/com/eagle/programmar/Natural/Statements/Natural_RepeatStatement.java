// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_RepeatStatement extends TokenSequence
{
	public @S(10) @DOC("sm/repeat.htm") Natural_Keyword REPEAT = new Natural_Keyword("REPEAT");
	public @S(20) TokenList<Natural_Statement> statements;
	public @S(30) Natural_Keyword ENDREPEAT = new Natural_Keyword("END-REPEAT");
}
