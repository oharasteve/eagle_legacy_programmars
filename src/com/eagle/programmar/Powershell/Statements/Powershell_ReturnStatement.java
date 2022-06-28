// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Powershell_ReturnStatement extends TokenSequence
{
	public @S(10) @NEWLINE Powershell_Keyword RETURN = new Powershell_Keyword("return");
	public @S(20) Powershell_Expression expr;
}
