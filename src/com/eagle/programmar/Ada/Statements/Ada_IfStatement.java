// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Ada_Keyword IF = new Ada_Keyword("if");
	public @S(20) Ada_Expression condition;
	public @S(30) Ada_Keyword THEN = new Ada_Keyword("then");
	public @S(40) TokenList<Ada_Statement> thenStatements;
	public @S(50) @OPT Ada_IfElseClause elseClause;
	public @S(60) Ada_Keyword END = new Ada_Keyword("end");
	public @S(70) Ada_Keyword IF2 = new Ada_Keyword("if");
	public @S(80) PunctuationSemicolon semicolon;

	public static class Ada_IfElseClause extends TokenSequence
	{
		public @S(10) Ada_Keyword ELSE = new Ada_Keyword("else");
		public @S(20) TokenList<Ada_Statement> elseStatements;
	}
}
