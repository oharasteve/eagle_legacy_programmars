// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_If_Statement extends TokenSequence
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#If_Statements") Delphi_Keyword IF = new Delphi_Keyword("If");
	public @S(20) Delphi_Expression cond;
	public @S(30) @OPT TokenList<Delphi_Comment> comments1;
	public @S(40) Delphi_Keyword THEN = new Delphi_Keyword("Then");
	public @S(50) @OPT TokenList<Delphi_Comment> comments2;
	public @S(60) @OPT Delphi_Statement stmt;
	public @S(70) @OPT Delphi_If_Else ifElse;
	
	public static class Delphi_If_Else extends TokenSequence
	{
		public @S(10) @OPT TokenList<Delphi_Comment> comments;
		public @S(20) Delphi_Keyword ELSE = new Delphi_Keyword("Else");
		public @S(30) Delphi_Statement stmt;
	}
}
