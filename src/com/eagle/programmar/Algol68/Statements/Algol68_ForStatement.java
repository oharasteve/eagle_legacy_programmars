// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_ForStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Algol68_Keyword FOR = new Algol68_Keyword("FOR");
	public @S(20) Algol68_Variable var;
	public @S(30) TokenList<Algol68_ForClause> clauses;
	public @S(40) Algol68_Keyword DO = new Algol68_Keyword("DO");
	public @S(50) TokenList<Algol68_Statement> statements;
	public @S(60) Algol68_Keyword OD = new Algol68_Keyword("OD");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static class Algol68_ForClause extends TokenSequence
	{
		public @S(10) Algol68_KeywordChoice FROM = new Algol68_KeywordChoice("FROM", "BY", "TO", "WHILE");
		public @S(20) Algol68_Expression expr;
	}
}
