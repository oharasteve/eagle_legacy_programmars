// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_FunctionCall extends TokenSequence
{
	public @S(10) Bash_WhatFunction what;
	public @S(20) @OPT TokenList<Bash_FunctionArg> args;
	
	public static class Bash_WhatFunction extends TokenChooser
	{
		public @CHOICE Bash_Filename fileName;
		public @CHOICE Bash_Variable variable;
	}
	
	public static class Bash_FunctionArg extends TokenChooser
	{
		public @CHOICE Bash_Literal literal;
		public @CHOICE Bash_Number number;
		public @CHOICE Bash_Filename fileName;
		public @CHOICE Bash_Variable variable;
	}
}
