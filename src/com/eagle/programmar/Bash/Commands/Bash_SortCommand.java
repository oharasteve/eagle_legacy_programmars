// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_SortCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword SORT = new Bash_Keyword("sort");
	public @S(20) @OPT TokenList<Bash_SortOption> options;
	public @S(30) @OPT Bash_Comment comment;

	public static class Bash_SortOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice RN = new Bash_KeywordChoice("-r", "-n");

		public @CHOICE static class Bash_SortOptionK extends TokenSequence
		{
			public @S(10) Bash_Keyword K = new Bash_Keyword("-k");
			public @S(20) Bash_Number n;
		}
	}
}
