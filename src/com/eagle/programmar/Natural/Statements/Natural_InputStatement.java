// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Option;
import com.eagle.programmar.Natural.Statements.Natural_DisplayStatement.Natural_DisplayElement;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_InputStatement extends TokenSequence
{
	public @S(10) @DOC("sm/input.htm") Natural_Keyword INPUT = new Natural_Keyword("INPUT");
	public @S(20) @OPT Natural_Keyword NO = new Natural_Keyword("NO");
	public @S(30) @OPT Natural_Keyword ERASE = new Natural_Keyword("ERASE");
	public @S(40) @OPT Natural_Option option;
	public @S(50) TokenList<Natural_DisplayElement> items;
}
