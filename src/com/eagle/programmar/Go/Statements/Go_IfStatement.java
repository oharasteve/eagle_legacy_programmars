// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Go_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#If_statements") Go_Keyword IF = new Go_Keyword("if");
	public @S(20) Go_Expression condition;
	public @S(30) Go_Statement thenStatement;
	public @S(40) @OPT Go_IfElseClause elseClause;

	public static class Go_IfElseClause extends TokenSequence
	{
		public @S(10) Go_Keyword ELSE = new Go_Keyword("else");
		public @S(20) Go_Statement elseStatement;
	}
}
