// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Powershell_BashCommand extends TokenSequence
{
	public @S(10) Powershell_Keyword BASH = new Powershell_Keyword("bash");
	public @S(20) Powershell_Expression expr;
}
