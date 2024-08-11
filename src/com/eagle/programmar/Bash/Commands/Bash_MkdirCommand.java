// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_MkdirCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword MKDIR = new Bash_Keyword("mkdir");
	public @S(20) @OPT TokenList<Bash_MkdirOption> options;
	public @S(30) Bash_FilenameOrLiteral fname;

	public static class Bash_MkdirOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-p");
	}
}
