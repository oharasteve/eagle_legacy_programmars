// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_SheBang;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Bash_BashProgram extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) Bash_SheBang shebang;
	public @S(20) @OPT SeparatedList<PunctuationSlash, Bash_Identifier_Reference> dir;
	public @S(30) @OPT PunctuationSlash slash;
	public @S(40) @OPT Bash_Keyword ENV = new Bash_Keyword("env");
	public @S(50) Bash_KeywordChoice BASH = new Bash_KeywordChoice("bash", "csh", "sh", "tcsh", "zsh");
	public @S(60) @OPT TokenList<Bash_BashOption> options;

	public static class Bash_BashOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-x", "-xe");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}
