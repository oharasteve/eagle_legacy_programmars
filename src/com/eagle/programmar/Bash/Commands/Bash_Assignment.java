// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_Assignment extends TokenSequence
{
	public @S(10) @OPT Bash_Keyword LOCAL = new Bash_Keyword("local");
	public @S(20) @DOC("#Shell-Arithmetic") @OPT Bash_Keyword LET = new Bash_Keyword("let");
	public @S(30) Bash_Variable variable;
	public @S(40) Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=", "+=", "-=");
	public @S(50) @OPT Bash_AssignWhat what;
	
	public static class Bash_AssignWhat extends TokenChooser
	{
		public @CHOICE Bash_Expression value;
		public @LAST Bash_Filename fname;
	}
}
