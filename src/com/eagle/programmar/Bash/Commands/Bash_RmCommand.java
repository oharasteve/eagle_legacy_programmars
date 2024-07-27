// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Bash_RmCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT Bash_RmBin rmBin;
	public @S(20) Bash_Keyword RM = new Bash_Keyword("rm");
	public @S(30) TokenList<Bash_RmOption> options;
	public @S(40) TokenList<Bash_FilenameOrLiteral> files;

	public static class Bash_RmOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-r", "-f", "-rf");
	}
	
	public static class Bash_RmBin extends TokenSequence
	{
		public @S(10) PunctuationSlash slash1;
		public @S(20) Bash_Keyword BIN = new Bash_Keyword("bin");
		public @S(30) PunctuationSlash slash2;
	}
}
