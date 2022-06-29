// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Powershell_Type extends TokenSequence
{
	public @S(10) Powershell_KeywordChoice INT = new Powershell_KeywordChoice("int");
}
