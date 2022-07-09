// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Ruby_IfStatement extends TokenSequence
{
	public @S(10) Ruby_Keyword IF = new Ruby_Keyword("if");
	public @S(20) Ruby_Expression condition;
	public @S(30) Ruby_EOLN eoln1;
	public @S(40) TokenList<Ruby_Statement> thenStatements;
	public @S(50) @OPT Ruby_IfElseClause elseClause;
	public @S(60) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(70) Ruby_EOLN eoln2;
	
	public static class Ruby_IfElseClause extends TokenSequence
	{
		public @S(10) Ruby_Keyword ELSE = new Ruby_Keyword("else");
		public @S(20) TokenList<Ruby_Statement> elseStatements;
	}
}
