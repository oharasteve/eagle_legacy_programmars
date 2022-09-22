// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_Type extends TokenSequence
{
	public @S(10) Powershell_KeywordChoice INT = new Powershell_KeywordChoice(
			"Array",
			"Char",
			"Double",
			"Int",
			"Int64",
			"PSCredential",
			"SecureString",
			"String");
	public @S(20) @OPT Powershell_TypeBrackets brackets;
	
	public static class Powershell_TypeBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}
}
