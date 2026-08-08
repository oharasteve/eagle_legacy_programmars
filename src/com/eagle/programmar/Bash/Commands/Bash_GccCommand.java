// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Bash_GccCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_KeywordChoice GCC = new Bash_KeywordChoice("gcc", "gfortran");
	public @S(20) @OPT TokenList<Bash_GccOption> options1;
	public @S(30) Bash_FilenameOrLiteral fileName;
	public @S(40) @OPT TokenList<Bash_GccOption> options2;

	public static class Bash_GccOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice(
				"-c",
				"-g",
				"-lm",
				"-Os",
				"-Wall");

		public @CHOICE static class Bash_GccOptionD extends TokenSequence
		{
			public @S(10) Bash_Punctuation D = new Bash_Punctuation("-D");
			public @S(20) Bash_Variable var;
			public @S(30) @OPT Bash_OptionDinit init;
			
			public static class Bash_OptionDinit extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) Bash_FilenameOrLiteral value;
			}
		}

		public @CHOICE static class Bash_GccOptionI extends TokenSequence
		{
			public @S(10) Bash_Keyword I = new Bash_Keyword("-I");
			public @S(20) Bash_FilenameOrLiteral fileName;
		}

		public @CHOICE static class Bash_GccOptionO extends TokenSequence
		{
			public @S(10) Bash_Keyword O = new Bash_Keyword("-o");
			public @S(20) Bash_FilenameOrLiteral fileName;
		}
	}
}
