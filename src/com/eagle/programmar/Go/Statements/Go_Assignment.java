// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Go_Assignment extends TokenSequence
{
	public @S(10) SeparatedList<Go_Variable,PunctuationComma> vars;
	public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Go_Expression value;
	public @S(40) Go_EOLN eoln;
}
