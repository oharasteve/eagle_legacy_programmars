// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_GccCommand extends TokenSequence
{
	public @S(10) Bash_KeywordChoice GCC = new Bash_KeywordChoice("gcc", "gfortran");
	public @S(20) @OPT TokenList<Bash_GccOption> options1;
	public @S(30) Bash_FilenameOrLiteral fileName;
	public @S(40) @OPT TokenList<Bash_GccOption> options2;

	public static class Bash_GccOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice G = new Bash_KeywordChoice("-g", "-Wall");

		public @CHOICE static class Bash_GccOptionO extends TokenSequence
		{
			public @S(10) Bash_Keyword O = new Bash_Keyword("-o");
			public @S(20) Bash_FilenameOrLiteral fileName;
		}
	}
}
