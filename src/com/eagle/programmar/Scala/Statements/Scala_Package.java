// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Symbols.Scala_Identifier;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;

public class Scala_Package extends TokenSequence
{
	public @S(10) Scala_Keyword PACKAGE = new Scala_Keyword("package");
	public @S(20) Scala_Identifier id;
	public @S(30) Scala_EOLN eoln;
}
