// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Command.CMD_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Identifier;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_PercentVariable;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_For_Statement extends TokenSequence
{
	public @S(10) @DOC("for.mspx") CMD_Keyword FOR = new CMD_Keyword("for");
	public @S(20) @OPT TokenList<CMD_For_Option> opts;
	public @S(30) CMD_PercentVariable var;
	public @S(40) CMD_Keyword IN = new CMD_Keyword("in");
	public @S(50) PunctuationLeftParen leftParen;
	public @S(60) CMD_Argument arg;
	public @S(70) @OPT TokenList<CMD_For_More_Params> moreParams;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) CMD_Keyword DO = new CMD_Keyword("do");
	public @S(100) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(110) CMD_Statement stmt;
	
	public static class CMD_For_More_Params extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) CMD_Argument arg;
	}
	
	public static class CMD_For_Option extends TokenChooser
	{
		public @CHOICE static class CMD_For_Option_D extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword D = new CMD_Keyword("d");
		}

		public @CHOICE static class CMD_For_Option_F extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword F = new CMD_Keyword("f");
			public @S(30) @OPT CMD_Literal opts;
		}
	
		public @CHOICE static class CMD_For_Option_L extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword L = new CMD_Keyword("l");
		}

		public @CHOICE static class CMD_For_Option_R extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword R = new CMD_Keyword("r");
			public @S(30) @OPT CMD_For_R forR;
			
			public static class CMD_For_R extends TokenChooser
			{
				public @CHOICE CMD_Literal dir;
				public @CHOICE CMD_Identifier dirName;
				
				public @CHOICE static class CMD_For_R_pct extends TokenSequence
				{
					public @S(10) CMD_Punctuation percent = new CMD_Punctuation('%');
					public @S(20) CMD_Number number;
				}
			}
		}
	}
}
