// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_IfStatement extends TokenSequence
{
	public @S(10) @DOC("taste-control-structures.html#ifelse") Scala_Keyword IF = new Scala_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) Scala_Statement thenStatement;
	public @S(60) @OPT Scala_IfElseClause elseClause;
	
	public static class Scala_IfElseClause extends TokenSequence
	{
		public @S(10) Scala_Keyword ELSE = new Scala_Keyword("else");
		public @S(20) Scala_Statement elseStatement;
	}
}
