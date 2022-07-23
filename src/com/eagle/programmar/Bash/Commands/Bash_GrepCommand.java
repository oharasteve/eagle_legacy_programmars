// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_GrepCommand extends TokenSequence
{
	public @S(10) Bash_KeywordChoice GREP = new Bash_KeywordChoice("grep", "egrep");
	public @S(20) @OPT TokenList<Bash_GrepOption> options;
	public @S(30) @OPT Bash_Literal pattern;
	public @S(40) @OPT Bash_FilenameOrLiteral filename;
	
	public static class Bash_GrepOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice I = new Bash_KeywordChoice("-i", "-v", "-E", "-q");
	}
}
