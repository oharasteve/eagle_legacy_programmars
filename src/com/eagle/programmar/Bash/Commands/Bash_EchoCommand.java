// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Terminals.Bash_EchoWhat;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_EchoCommand extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
	public @S(20) @OPT TokenList<Bash_EchoOption> options;
	public @S(30) @OPT Bash_EchoWhat what;

	public static class Bash_EchoOption extends TokenChooser
	{
		public @CHOICE Bash_Keyword XXopt = new Bash_Keyword("-n");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = interpreter.getStrValue(what);
		System.out.println(result);
	}
}
