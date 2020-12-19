// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Option.Natural_OptionChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_FormatStatement extends TokenSequence
{
	public @S(10) @DOC("sm/format.htm") Natural_Keyword FORMAT = new Natural_Keyword("FORMAT");
	public @S(20) TokenList<Natural_OptionChoice> options;
}
