// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMD_Grep_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) CMD_Keyword GREP = new CMD_Keyword("grep");
	public @S(20) TokenList<CMD_Grep_Parameter> params;

	public static class CMD_Grep_Parameter extends TokenChooser
	{
		public @CHOICE CMD_Expression XXsrcFile;

		public @CHOICE static class CMD_Grep_Option_e extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword E = new CMD_Keyword("e");
			public @S(30) CMD_Expression pattern;
		}

		public @CHOICE static class CMD_Grep_Option_H extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword H = new CMD_Keyword("H");
		}

		public @CHOICE static class CMD_Grep_Option_i extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword I = new CMD_Keyword("i");
		}

		public @CHOICE static class CMD_Grep_Option_l extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword L = new CMD_Keyword("l");
		}

		public @CHOICE static class CMD_Grep_Option_n extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword N = new CMD_Keyword("n");
		}

		public @CHOICE static class CMD_Grep_Option_v extends TokenSequence
		{
			public @S(10) CMD_Punctuation hyphen = new CMD_Punctuation('-');
			public @S(20) CMD_Keyword V = new CMD_Keyword("v");
		}

		public @CHOICE static class CMD_Grep_Option_w extends TokenSequence
		{
			public @S(10) PunctuationHyphen minus;
			public @S(20) CMD_Keyword W = new CMD_Keyword("w");
		}
	}
}
