// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_CompressStatement extends TokenSequence
{
	public @S(10) @DOC("sm/compress.htm") Natural_Keyword COMPRESS = new Natural_Keyword("COMPRESS");
	public @S(20) TokenList<Natural_Expression> expr;
	public @S(30) Natural_Keyword INTO = new Natural_Keyword("INTO");
	public @S(40) Natural_Variable var;
}
