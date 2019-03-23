// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Powershell_ForEachStatement extends TokenSequence
{
	public @NEWLINE Powershell_Keyword PRINT = new Powershell_Keyword("print");
	public Powershell_Expression expr;
	public @NOSPACE PunctuationSemicolon semicolon;
}
