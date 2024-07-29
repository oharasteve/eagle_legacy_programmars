// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Powershell.Commands;

import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Powershell_StandardOption extends TokenChooser
{
	public @CHOICE static class Powershell_ErrorAction extends TokenSequence
	{
		public @S(10) Powershell_Keyword ERROR = new Powershell_Keyword("-ErrorAction");
		public @S(20) Powershell_KeywordChoice ACTION = new Powershell_KeywordChoice(
				"Ignore", "SilentlyContinue");
	}
}
