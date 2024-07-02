// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Rmdir_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("rmdir.mspx") CMD_Keyword RMDIR = new CMD_Keyword("rmdir");
	public @S(20) @OPT TokenList<CMD_Rmdir_Option> opts;
	public @S(30) CMD_Argument dir;

	public static class CMD_Rmdir_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Rmdir_Option_Q extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword Q = new CMD_Keyword("q");
		}

		public @CHOICE static class CMD_Rmdir_Option_S extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword S = new CMD_Keyword("s");
		}
	}
}
