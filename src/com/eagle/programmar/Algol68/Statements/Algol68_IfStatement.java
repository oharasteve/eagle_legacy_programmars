// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_IfStatement extends TokenSequence
{
	public @S(10) Algol68_Keyword IF = new Algol68_Keyword("IF");
	public @S(20) Algol68_Expression condition;
	public @S(30) Algol68_Keyword THEN = new Algol68_Keyword("THEN");
	public @S(40) TokenList<Algol68_Statement> thenStatements;
	public @S(50) @OPT TokenList<Algol68_IfElifClause> elifClause;
	public @S(60) @OPT Algol68_IfElseClause elseClause;
	public @S(70) Algol68_Keyword END = new Algol68_Keyword("FI");
	public @S(80) @OPT PunctuationSemicolon semicolon;

	public static class Algol68_IfElifClause extends TokenSequence
	{
		public @S(10) Algol68_Keyword ELIF = new Algol68_Keyword("ELIF");
		public @S(20) Algol68_Expression condition;
		public @S(30) Algol68_Keyword THEN = new Algol68_Keyword("THEN");
		public @S(40) TokenList<Algol68_Statement> elseStatements;
	}

	public static class Algol68_IfElseClause extends TokenSequence
	{
		public @S(10) Algol68_Keyword ELSE = new Algol68_Keyword("ELSE");
		public @S(20) TokenList<Algol68_Statement> elseStatements;
	}
}
