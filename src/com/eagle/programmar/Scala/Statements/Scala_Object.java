// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
import com.eagle.programmar.Scala.Symbols.Scala_Object_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;

public class Scala_Object extends TokenSequence
{
	public @S(10) Scala_Keyword OBJECT = new Scala_Keyword("object");
	public @S(20) Scala_Object_Definition obj;
	public @S(30) Scala_Keyword EXTENDS = new Scala_Keyword("extends");
	public @S(40) Scala_Identifier_Reference parent;
	public @S(50) Scala_BlockStatement statement;
}
