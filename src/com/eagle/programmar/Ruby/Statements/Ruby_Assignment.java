// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class Ruby_Assignment extends TokenSequence
{
	public @S(10) Ruby_Variable var;
	public @S(20) Ruby_PunctuationChoice equals = new Ruby_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ruby_Expression value;
	public @S(40) Ruby_EOLN eoln;
}
