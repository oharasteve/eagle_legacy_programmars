// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_RmCommand extends TokenSequence
{
	public @S(10) Bash_Keyword RM = new Bash_Keyword("rm");
	public @S(20) TokenList<Bash_RmOption> options;
	public @S(30) TokenList<Bash_FilenameOrLiteral> files;
	
	public static class Bash_RmOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice RF = new Bash_KeywordChoice("-r", "-f", "-rf");
	}
}
