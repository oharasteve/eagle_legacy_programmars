// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_SingleOrMultiLineStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_IfStatement extends TokenSequence
{
	public @S(10) @DOC("compound_stmts.html#the-if-statement") @NOSPACE Python_Keyword IF = new Python_Keyword("if");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) Python_SingleOrMultiLineStatement ifThen;
	public @S(50) @OPT TokenList<Python_IfElif> ifElif;
	public @S(60) @OPT Python_IfElse ifElse;
	public @S(70) @OPT TokenList<Python_Comment> comments;
	
	public static class Python_IfElif extends TokenSequence
	{
		public @S(10) @NEWLINE Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) @NOSPACE Python_Keyword ELIF = new Python_Keyword("elif");
		public @S(30) Python_Expression condition;
		public @S(40) @NOSPACE PunctuationColon colon;
		public @S(50) Python_SingleOrMultiLineStatement elifStatement;
	}
		
	public static class Python_IfElse extends TokenSequence
	{
		public @S(10) @NEWLINE Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) @NOSPACE Python_Keyword ELSE = new Python_Keyword("else");
		public @S(30) @NOSPACE PunctuationColon colon;
		public @S(40) Python_SingleOrMultiLineStatement ifElseStatement;
	}
}
