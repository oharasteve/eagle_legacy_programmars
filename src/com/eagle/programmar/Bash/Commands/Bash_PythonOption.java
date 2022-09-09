// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_PythonOption extends TokenChooser
{
	public @CHOICE static class Bash_PythonOptionM extends TokenSequence
	{
		public @S(10) Bash_Keyword M = new Bash_Keyword("-m");
		public @S(20) Bash_Identifier_Reference moduleName;
	}
}
