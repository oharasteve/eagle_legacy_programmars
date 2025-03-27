// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Python_DeleteStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("simple_stmts.html#the-del-statement") Python_Keyword DEL = new Python_Keyword("del");
	public @S(20) Python_ExpressionList expressionList;
}
