// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_RestOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_EchoCommand extends TokenSequence
{
	public @S(10) @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
	public @S(20) @OPT Bash_EchoWhat what;
	
	public static class Bash_EchoWhat extends TokenChooser
	{
		public @LAST Bash_RestOfLine restOfLine;

		public @CHOICE static class Bash_Literals extends TokenSequence
		{
			public @S(10) TokenList<Bash_Literal> lines;
		}
	}
}
