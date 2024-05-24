// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_WriteStatement extends TokenSequence
{
	public @S(10) @DOC("sm/write.htm") Natural_Keyword WRITE = new Natural_Keyword("WRITE");
	public @S(20) @OPT Natural_KeywordChoice TITLE = new Natural_KeywordChoice("TITLE", "NOTITLE");
	public @S(30) TokenList<Natural_DisplayElement> writeWhat;
}
