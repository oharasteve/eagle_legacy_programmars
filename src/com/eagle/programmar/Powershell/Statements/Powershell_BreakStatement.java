// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Powershell_BreakStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#851-the-break-statement") Powershell_Keyword BREAK = new Powershell_Keyword("Break");
}
