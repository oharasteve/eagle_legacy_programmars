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

public class CMD_Copy_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("copy.mspx") CMD_Keyword COPY = new CMD_Keyword("copy");
	public @S(20) @OPT TokenList<CMD_Copy_Option> opts;
	public @S(30) CMD_Argument copyFrom;
	public @S(40) @OPT CMD_Argument copyTo;

	public static class CMD_Copy_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Copy_Option_Y extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword Y = new CMD_Keyword("y");
		}
	}
}
