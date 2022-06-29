// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Powershell_WriteStatement extends TokenSequence
{
	public @S(10) @NEWLINE Powershell_KeywordChoice WRITE = new Powershell_KeywordChoice(
			"write-host", "write-output");
	public @S(20) Powershell_Expression expr;
}
