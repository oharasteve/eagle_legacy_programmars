// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Powershell_XcopyStatement extends TokenSequence
{
	public @S(10) Powershell_Keyword XCOPY = new Powershell_Keyword("XCOPY");
	public @S(20) Powershell_Literal source;
	public @S(30) Powershell_Literal target;
	public @S(40) @OPT TokenList<Powershell_XcopyOption> options;
	
	public static class Powershell_XcopyOption extends TokenChooser
	{
		public @CHOICE static class Powershell_XcopyOptionLetter extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) Powershell_KeywordChoice OPT = new Powershell_KeywordChoice(
					"D", "E", "F", "H", "I", "R", "V", "Y");
		}
		
		public @CHOICE static class Powershell_XcopyOptionExclude extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) Powershell_Keyword EXCLUDE = new Powershell_Keyword("EXCLUDE");
			public @S(30) PunctuationColon colon;
			public @S(40) Powershell_Filename filename;
		}
	}
}
