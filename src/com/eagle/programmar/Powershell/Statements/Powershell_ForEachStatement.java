// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Powershell_ForEachStatement extends TokenSequence
{
	public @S(10) Powershell_Keyword FOREACH = new Powershell_Keyword("foreach");
	public @S(20) Powershell_Expression expr;
	public @S(30) PunctuationSemicolon semicolon;
}
