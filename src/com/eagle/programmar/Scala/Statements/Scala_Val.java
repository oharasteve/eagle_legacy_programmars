// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Type;
import com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Scala_Val extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("taste-vars-data-types.html#two-types-of-variables") Scala_Keyword VAL = new Scala_Keyword(
			"val");
	public @S(20) Scala_Variable_Definition id;
	public @S(30) PunctuationColon colon;
	public @S(40) Scala_Type type;
	public @S(50) PunctuationEquals equals;
	public @S(60) Scala_Expression initValue;
	public @S(70) Scala_EOLN eoln;
}
