// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_NMake_Statement extends TokenSequence
{
	public @S(10) CMD_Keyword NMAKE = new CMD_Keyword("nmake");
	public @S(20) TokenList<CMD_NMake_Parameter> params;
	
	public static class CMD_NMake_Parameter extends TokenChooser
	{
		public @CHOICE CMD_Argument target;

		public @CHOICE static class CMD_NMake_Option_I extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword I = new CMD_Keyword("i");
		}

		public @CHOICE static class CMD_NMake_Option_K extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword K = new CMD_Keyword("k");
		}

		public @CHOICE static class CMD_NMake_Option_E extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword E = new CMD_Keyword("e");
		}


		public @CHOICE static class CMD_NMake_Option_F extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword F = new CMD_Keyword("f");
			public @S(30) CMD_Argument makefile;
		}
	}
}
