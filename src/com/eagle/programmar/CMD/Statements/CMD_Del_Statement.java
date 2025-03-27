// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Del_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("del.mspx") CMD_Keyword DEL = new CMD_Keyword("del");
	public @S(20) @OPT TokenList<CMD_Del_Option> opts;
	public @S(30) TokenList<CMD_Expression> file;

	public static class CMD_Del_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Del_Option_F extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword F = new CMD_Keyword("f");
		}

		public @CHOICE static class CMD_Del_Option_Q extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword Q = new CMD_Keyword("q");
		}

		public @CHOICE static class CMD_Del_Option_S extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword S = new CMD_Keyword("s");
		}
	}
}
