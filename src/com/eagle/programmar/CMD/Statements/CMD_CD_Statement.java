// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 16, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_CD_Statement extends TokenSequence
{
	public @S(10) @DOC("chdir.mspx") CMD_Keyword CD = new CMD_Keyword("cd");
	public @S(20) @OPT TokenList<CMD_CD_Option> opts;
	public @S(30) CMD_Argument dir;
	
	public static class CMD_CD_Option extends TokenChooser
	{
		public @CHOICE static class CMD_CD_Option_D extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword D = new CMD_Keyword("d");
		}
	}
}
