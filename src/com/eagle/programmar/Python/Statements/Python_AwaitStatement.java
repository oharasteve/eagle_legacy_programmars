// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2022

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;

public class Python_AwaitStatement extends TokenSequence
{
	public @S(10) @OPT Python_IfAwait ifAwait;
	public @S(20) Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(30) Python_Expression condition;
	
	public static class Python_IfAwait extends TokenSequence
	{
		public @S(10) Python_Keyword IF = new Python_Keyword("if");
		public @S(20) @OPT Python_Keyword NOT = new Python_Keyword("not");
	}
}
