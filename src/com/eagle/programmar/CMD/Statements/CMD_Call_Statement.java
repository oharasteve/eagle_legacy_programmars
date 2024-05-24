// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Call_Statement extends TokenSequence
{
	public @S(10) @DOC("call.mspx") CMD_Keyword CALL = new CMD_Keyword("call");
	public @S(20) @OPT PunctuationColon colon;
	public @S(30) CMD_Argument what;
	public @S(40) @OPT TokenList<CMD_Call_Parameter> args;

	public static class CMD_Call_Parameter extends TokenChooser
	{
		public @CHOICE CMD_Argument arg;

		public @CHOICE static class CMD_Call_Minus_Option extends TokenSequence
		{
			public @S(10) PunctuationHyphen minus;
			public @S(20) CMD_Argument option;
		}

		public @CHOICE static class CMD_Call_Slash_Option extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Argument option;
		}
	}
}
