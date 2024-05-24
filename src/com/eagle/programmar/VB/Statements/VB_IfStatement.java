// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Statement.VB_BaseStatement;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_IfStatement extends TokenSequence
{
	public @S(10) @DOC("752y8abs.aspx") VB_Keyword IF1 = new VB_Keyword("if");
	public @S(20) VB_Expression condition;
	public @S(30) VB_Keyword THEN = new VB_Keyword("then");
	public @S(40) VB_IfType ifType;

	public static class VB_IfType extends TokenChooser
	{
		public @CHOICE static class VB_IfOneLiner extends TokenSequence
		{
			public @S(10) VB_BaseStatement thenStatement;
		}

		public @CHOICE static class VB_IfMultiLiner extends TokenSequence
		{
			public @S(10) VB_EndOfLine eoln;
			public @S(20) TokenList<VB_Statement> thenStatement;
			public @S(30) @OPT TokenList<VB_IfElseIfClause> elseIfClause;
			public @S(40) @OPT VB_IfElseClause elseClause;
			public @S(50) VB_Keyword END = new VB_Keyword("end");
			public @S(60) VB_Keyword IF2 = new VB_Keyword("if");
		}
	}

	public static class VB_IfElseIfClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<VB_Comment> comments;
		public @S(20) VB_Keyword ELSEIF = new VB_Keyword("elseif");
		public @S(30) VB_Expression condition;
		public @S(40) VB_Keyword THEN = new VB_Keyword("then");
		public @S(50) VB_EndOfLine eoln;
		public @S(60) TokenList<VB_Statement> elseIfStatement;
	}

	public static class VB_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<VB_Comment> comments;
		public @S(20) VB_Keyword ELSE = new VB_Keyword("else");
		public @S(30) VB_EndOfLine eoln;
		public @S(40) TokenList<VB_Statement> elseStatement;
	}
}
