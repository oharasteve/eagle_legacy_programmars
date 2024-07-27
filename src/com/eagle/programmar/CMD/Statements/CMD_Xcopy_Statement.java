// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2022

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Filename;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Xcopy_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) CMD_Keyword XCOPY = new CMD_Keyword("XCOPY");
	public @S(20) CMD_Literal source;
	public @S(30) CMD_Literal target;
	public @S(40) @OPT TokenList<CMD_XcopyOption> options;

	public static class CMD_XcopyOption extends TokenChooser
	{
		public @CHOICE static class Powershell_XcopyOptionLetter extends TokenSequence
		{
			public @S(10) PunctuationSlash XXslash;
			public @S(20) CMD_KeywordChoice XXopt = new CMD_KeywordChoice("D", "E", "F", "H", "I", "R", "V", "Y");
		}

		public @CHOICE static class CMD_XcopyOptionExclude extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword EXCLUDE = new CMD_Keyword("EXCLUDE");
			public @S(30) PunctuationColon colon;
			public @S(40) CMD_Filename filename;
		}
	}
}
