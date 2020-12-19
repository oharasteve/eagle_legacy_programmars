// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Filename;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_AtAtStatement extends TokenSequence
{
	public @S(10) SQL_Punctuation atat = new SQL_Punctuation("@@");
	public @S(20) SQL_Filename filename;
	public @S(30) @OPT PunctuationSemicolon semicolon;
}
