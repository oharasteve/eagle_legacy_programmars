// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

//[CmdletBinding()]
//Param(
//   [Parameter(Mandatory=$False,Position=1)]$param,
//   [Switch]$night = $False
//)

public class Powershell_CmdletBinding extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) Powershell_Keyword CMDLETBINDING = new Powershell_Keyword("CmdletBinding");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationRightBracket rightBracket;
	public @S(60) @OPT Powershell_EndOfLine eoln;
}
