// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 16, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_WcCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword WC = new Bash_Keyword("wc");
	public @S(20) @OPT TokenList<Bash_WcOption> options;
	public @S(30) @OPT TokenList<Bash_FilenameOrLiteral> files;

	public static class Bash_WcOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-l", "-c");
	}
}
