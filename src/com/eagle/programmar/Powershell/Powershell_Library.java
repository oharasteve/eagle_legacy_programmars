// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_Library extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) SeparatedList<Powershell_Identifier_Reference, PunctuationPeriod> name;
	public @S(30) @OPT Powershell_Keyword FTP = new Powershell_Keyword("+FTP");
	public @S(40) PunctuationRightBracket rightBracket;
	public @S(50) Powershell_Punctuation colons = new Powershell_Punctuation("::");
}
