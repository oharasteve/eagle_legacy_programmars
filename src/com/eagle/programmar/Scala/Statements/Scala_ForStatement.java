// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_ForStatement extends TokenSequence
{
	public @S(10) @DOC("taste-control-structures.html#for-loops-and-expressions") Scala_Keyword FOR = new Scala_Keyword(
			"for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Variable var;
	public @S(40) Scala_Punctuation arrow = new Scala_Punctuation("<-");
	public @S(50) Scala_Expression values;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) Scala_Statement statement;

}
