// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Julia_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("manual/control-flow/#man-conditional-evaluation") Julia_Keyword IF = new Julia_Keyword("if");
	public @S(20) Julia_Expression condition;
	public @S(30) Julia_EOLN eoln1;
	public @S(40) TokenList<Julia_Statement> thenStatements;
	public @S(50) @OPT Julia_IfElseClause elseClause;
	public @S(60) Julia_Keyword END = new Julia_Keyword("end");
	public @S(70) Julia_EOLN eoln2;

	public static class Julia_IfElseClause extends TokenSequence
	{
		public @S(10) Julia_Keyword ELSE = new Julia_Keyword("else");
		public @S(20) @OPT Julia_EOLN eoln2;
		public @S(30) TokenList<Julia_Statement> elseStatements;
	}
}
