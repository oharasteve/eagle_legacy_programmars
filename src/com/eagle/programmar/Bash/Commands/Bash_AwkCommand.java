// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_AwkCommand extends TokenSequence
{
	public @S(10) Bash_Keyword AWK = new Bash_Keyword("awk");
	public @S(20) @OPT TokenList<Bash_AwkOption> options;
	public @S(30) @OPT Bash_Filename fileName;
	
	public static class Bash_AwkOption extends TokenChooser
	{
		public @CHOICE static class Bash_AwkOptionF extends TokenSequence
		{
			public @S(10) Bash_Keyword F = new Bash_Keyword("-f");
			public @S(20) Bash_Filename fileName;
		}
	}
}
