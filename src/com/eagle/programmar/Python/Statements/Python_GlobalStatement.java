// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_GlobalStatement extends TokenSequence
{
	public @S(10) @DOC("simple_stmts.html#the-global-statement") Python_KeywordChoice GLOBAL = new Python_KeywordChoice(
			"global", "nonlocal");
	public @S(20) SeparatedList<Python_Variable_Definition, PunctuationComma> vars;
}
