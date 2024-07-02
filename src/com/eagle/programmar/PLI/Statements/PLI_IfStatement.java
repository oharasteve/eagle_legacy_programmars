// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class PLI_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT PLI_Label label;
	public @S(20) @DOC("7.27") PLI_Keyword IF = new PLI_Keyword("IF");
	public @S(30) PLI_Expression cond;
	public @S(40) PLI_Keyword THEN = new PLI_Keyword("THEN");
	public @S(50) @OPT TokenList<PLI_Comment> comment1;
	public @S(60) PLI_Statement thenAction;
	public @S(70) @OPT TokenList<PLI_Comment> comment2;
	public @S(80) @OPT PLI_Else elseClause;
	public @S(90) @OPT PLI_Keyword ENDIF = new PLI_Keyword("END-IF");

	public static class PLI_Else extends TokenSequence
	{
		public @S(10) PLI_Keyword ELSE = new PLI_Keyword("ELSE");
		public @S(20) @OPT TokenList<PLI_Comment> comment3;
		public @S(30) PLI_Statement elseAction;
		public @S(40) @OPT TokenList<PLI_Comment> comment4;
	}
}
