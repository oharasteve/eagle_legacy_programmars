// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2014

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_AssertStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("simple_stmts.html#the-assert-statement") Python_Keyword ASSERT = new Python_Keyword("assert");
	public @S(20) Python_Expression condition;
	public @S(30) @OPT Python_AssertMessage message;

	public static class Python_AssertMessage extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Python_Expression message;
	}
}
