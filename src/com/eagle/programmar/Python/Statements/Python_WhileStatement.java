// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_WhileStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("compound_stmts.html#the-while-statement") @NOSPACE Python_Keyword WHILE = new Python_Keyword(
			"while");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) Python_StatementBlock statements;
	public @S(50) @OPT Python_WhileElse whileElse;

	public static class Python_WhileElse extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(40) PunctuationColon colon;
		public @S(50) Python_StatementBlock doWhat;
	}
}
