// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CMD_GCC_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) CMD_Keyword GCC = new CMD_Keyword("gcc");
	public @S(20) TokenList<CMD_GCC_Parameter> params;

	public static class CMD_GCC_Parameter extends TokenChooser
	{
		public @CHOICE CMD_Argument XXsrcFile;

		public @CHOICE static class CMD_GCC_Option_O extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword O = new CMD_Keyword("o");
			public @S(30) CMD_Argument tgtFile;
		}
	}
}
