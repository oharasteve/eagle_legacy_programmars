// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Scala_BreakStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Scala_Keyword BREAK = new Scala_Keyword("break");
	public @S(20) Scala_EOLN eoln;
}
