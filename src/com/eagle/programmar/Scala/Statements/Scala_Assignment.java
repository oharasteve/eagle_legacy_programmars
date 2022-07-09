// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class Scala_Assignment extends TokenSequence
{
	public @S(10) Scala_Variable var;
	public @S(20) Scala_PunctuationChoice equals = new Scala_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Scala_Expression value;
	public @S(40) Scala_EOLN eoln;
}
