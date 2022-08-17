// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2022

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;

public class Python_AwaitStatement extends TokenSequence
{
	public @S(10) Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(20) Python_Expression condition;
}
