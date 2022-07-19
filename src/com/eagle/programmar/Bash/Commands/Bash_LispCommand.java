// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_LispCommand extends TokenSequence
{
	public @S(10) Bash_Keyword LISP = new Bash_Keyword("clisp");
	public @S(20) @OPT TokenList<Bash_LispOption> options;
	public @S(30) @OPT Bash_Filename fileName;
	
	public static class Bash_LispOption extends TokenChooser
	{
		public @CHOICE Bash_Keyword Q = new Bash_Keyword("-q");

		public @CHOICE static class Bash_LispOptionX extends TokenSequence
		{
			public @S(10) Bash_Keyword X = new Bash_Keyword("-x");
			public @S(20) Bash_Literal command;
		}
	}
}
