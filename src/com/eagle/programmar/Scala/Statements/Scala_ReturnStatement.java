// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;

public class Scala_ReturnStatement extends TokenSequence
{
	public @S(10) Scala_Keyword RETURN = new Scala_Keyword("return");
	public @S(20) Scala_Expression expr;
	public @S(30) Scala_EOLN eoln;
}
