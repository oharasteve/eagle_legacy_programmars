// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta.Statements;

import com.eagle.programmar.Gupta.Gupta_Condition;
import com.eagle.programmar.Gupta.Gupta_Statement;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Gupta_If_Statement extends TokenSequence
{
	public @S(10) Gupta_Keyword If = new Gupta_Keyword("If");
	public @S(20) Gupta_Condition condition;
	public @S(30) TokenList<Gupta_Statement> thenStatements;
	public @S(40) @OPT Gupta_Else_Statement elseClause;
	
	public static class Gupta_Else_Statement extends TokenSequence
	{
		public @S(10) Gupta_Keyword Else = new Gupta_Keyword("Else");
		public @S(20) TokenList<Gupta_Statement> elseStatements;
	}
}
