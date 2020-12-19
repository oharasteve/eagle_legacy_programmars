// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_GoStatement extends TokenSequence
{
	public @S(10) @DOC("7.25") PLI_Keyword GO = new PLI_Keyword("GO");
	public @S(20) PLI_Keyword TO = new PLI_Keyword("TO");
	public @S(30) PLI_Identifier_Reference label;
	public @S(40) PunctuationSemicolon semicolon;
}
