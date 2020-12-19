// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_ForStatement extends TokenSequence
{
	public @S(10) @NOSPACE Python_Keyword FOR = new Python_Keyword("for");
	public @S(20) Python_VariableList varList;
	public @S(30) Python_Keyword IN = new Python_Keyword("in");
	public @S(40) Python_ExpressionList expressionList;
	public @S(50) @NOSPACE PunctuationColon colon;
	public @S(60) @OPT Python_Comment comment;
	public @S(70) Python_SingleOrMultiLineStatement forType;
	public @S(80) @OPT Python_ForElse forElseStatement;

	public static class Python_ForElse extends TokenSequence
	{
		public @S(10) Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement doWhat;
	}
}
