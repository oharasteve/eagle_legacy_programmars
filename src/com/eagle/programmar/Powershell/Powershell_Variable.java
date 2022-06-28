// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Powershell_Variable extends TokenSequence
{
	public @S(10) Powershell_Punctuation DOLLAR = new Powershell_Punctuation("$");
	public @S(20) @OPT Powershell_VariableScope scope;
	public @S(30) Powershell_Identifier_Reference id;
	
	public static class Powershell_VariableScope extends TokenSequence
	{
		public @S(10) Powershell_KeywordChoice SCRIPT = new Powershell_KeywordChoice("script");
		public @S(20) PunctuationColon colon;
	}
}
