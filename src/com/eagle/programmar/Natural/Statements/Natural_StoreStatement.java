// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_StoreStatement extends TokenSequence
{
	public @S(10) @OPT Natural_Label label;
	public @S(20) @DOC("sm/store.htm") Natural_Keyword STORE = new Natural_Keyword("STORE");
	public @S(30) @OPT Natural_Keyword RECORD = new Natural_Keyword("RECORD");
	public @S(40) @OPT Natural_Keyword IN = new Natural_Keyword("IN");
	public @S(50) @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
	public @S(60) Natural_Identifier_Reference viewName;
}
