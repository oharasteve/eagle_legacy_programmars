// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_LeaveStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("7.30") PLI_Keyword LEAVE = new PLI_Keyword("LEAVE");
	public @S(20) @OPT PLI_Identifier_Reference label;
	public @S(30) PunctuationSemicolon semicolon;
}
