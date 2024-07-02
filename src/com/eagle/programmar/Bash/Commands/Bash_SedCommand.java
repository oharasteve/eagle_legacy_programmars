// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_SedCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword SED = new Bash_Keyword("sed");
	public @S(20) @OPT TokenList<Bash_SedOption> options;
	public @S(30) @OPT Bash_Literal commands;
	public @S(40) @OPT Bash_FilenameOrLiteral filename;

	public static class Bash_SedOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice E = new Bash_KeywordChoice("-E", "-e", "-i", "-r");

		public @CHOICE static class Bash_SedOptionF extends TokenSequence
		{
			public @S(10) Bash_Keyword F = new Bash_Keyword("-f");
			public @S(20) Bash_FilenameOrLiteral fileName;
		}

		public @CHOICE static class Bash_SedOptionPlus extends TokenSequence
		{
			public @S(10) Bash_Punctuation plus = new Bash_Punctuation("+");
			public @S(20) Bash_KeywordChoice EX = new Bash_KeywordChoice("e", "ex", "x");
		}
	}
}
