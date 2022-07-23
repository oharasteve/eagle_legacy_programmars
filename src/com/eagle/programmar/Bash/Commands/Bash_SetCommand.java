// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_SetCommand extends TokenSequence
{
	public @S(10) Bash_Keyword SET = new Bash_Keyword("set");
	public @S(20) @OPT TokenList<Bash_SetOption> options;
	public @S(30) @OPT Bash_Comment comment;
	
	public static class Bash_SetOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice EUX = new Bash_KeywordChoice(
				"-e", "-eu", "-ex", "-eE", "-eux", "-u", "-x");

		public @CHOICE static class Bash_SetPlus extends TokenSequence
		{
			public @S(10) Bash_Punctuation PLUS = new Bash_Punctuation("+");
			public @S(20) Bash_KeywordChoice EX = new Bash_KeywordChoice("e", "ex");
		}

		public @CHOICE static class Bash_SetOptionO extends TokenSequence
		{
			public @S(10) Bash_KeywordChoice O = new Bash_KeywordChoice(
					"-eo", "-euox", "-o");
			public @S(20) Bash_Identifier_Reference id;
		}
	}
}
