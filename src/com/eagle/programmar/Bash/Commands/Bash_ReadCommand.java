// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_ReadCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword READ = new Bash_Keyword("read");
	public @S(20) @OPT TokenList<Bash_ReadOption> options;
	public @S(30) TokenList<Bash_Identifier_Reference> ids;

	public static class Bash_ReadOption extends TokenChooser
	{
		public @CHOICE static class Bash_ReadPrompt extends TokenSequence
		{
			public @S(10) Bash_KeywordChoice opt = new Bash_KeywordChoice("-p");
			public @S(20) Bash_Literal prompt;
		}
	}
}
