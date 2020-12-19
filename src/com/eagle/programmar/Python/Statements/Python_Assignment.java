// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_Assignment extends TokenSequence
{
	public @S(10) @NOSPACE Python_VariableList varList;
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
	public @S(30) Python_Expression expr;
	public @S(40) @OPT TokenList<Python_MoreAsgExpressions> moreExpressions;
	public @S(50) @OPT Python_Comment comment;
	
	public static class Python_MoreAsgExpressions extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Python_Expression expr;
	}
}
