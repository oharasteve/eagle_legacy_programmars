// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 24, 2022

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_FindStr_Statement extends TokenSequence
{
	public @S(10) CMD_Keyword FINDSTR = new CMD_Keyword("FINDSTR");
	public @S(20) @OPT TokenList<CMD_FindStrOption> options;
	public @S(30) CMD_Literal pattern;

	public static class CMD_FindStrOption extends TokenChooser
	{
		public @CHOICE static class Powershell_FindStrOptionLetter extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_KeywordChoice OPT = new CMD_KeywordChoice("R");
		}
	}
}
