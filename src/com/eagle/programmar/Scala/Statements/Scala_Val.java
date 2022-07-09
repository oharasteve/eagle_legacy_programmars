// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Scala_Val extends TokenSequence
{
	public @S(10) Scala_Keyword VAR = new Scala_Keyword("var");
	public @S(20) Scala_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Scala_Expression value;
	public @S(50) Scala_EOLN eoln;
}
