// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_GitCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword GIT = new Bash_Keyword("git");
	public @S(20) @OPT TokenList<Bash_GitOption> options;

	public static class Bash_GitOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice HEAD = new Bash_KeywordChoice(
				"ls-tree", "-r", "-t", "-l", "--full-name", "HEAD");
	}
}
