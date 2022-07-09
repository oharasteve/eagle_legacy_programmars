// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;

public class Scala_BreakableStatement extends TokenSequence
{
	public @S(10) Scala_Keyword BREAKABLE = new Scala_Keyword("breakable");
	public @S(20) Scala_BlockStatement block;
}
