// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_ForStatement extends TokenSequence
{
	public @S(10) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(20) @DOC("compound_stmts.html#the-for-statement") @NOSPACE Python_Keyword FOR = new Python_Keyword("for");
	public @S(30) Python_ForWhat what;
	public @S(40) Python_Keyword IN = new Python_Keyword("in");
	public @S(50) Python_ExpressionList expressionList;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) @OPT Python_Comment comment;
	public @S(80) Python_SingleOrMultiLineStatement forType;
	public @S(90) @OPT Python_ForElse forElseStatement;

	public static class Python_ForWhat extends TokenChooser
	{
		public @CHOICE Python_VariableList varList;
		
		public @CHOICE static class Python_ForList extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Python_VariableList varList;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}
	
	public static class Python_ForElse extends TokenSequence
	{
		public @S(10) Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(30) PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement doWhat;
	}
}
