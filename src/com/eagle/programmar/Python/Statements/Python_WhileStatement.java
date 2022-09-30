// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_WhileStatement extends TokenSequence
{
	public @S(10) @DOC("compound_stmts.html#the-while-statement") @NOSPACE Python_Keyword WHILE = new Python_Keyword("while");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) Python_SingleOrMultiLineStatement statements;
	public @S(50) @OPT Python_WhileElse whileElse;
	
	public static class Python_WhileElse extends TokenSequence
	{
		public @S(10) Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement doWhat;
	}
}
