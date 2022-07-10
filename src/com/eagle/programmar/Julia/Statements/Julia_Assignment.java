// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class Julia_Assignment extends TokenSequence
{
	public @S(10) Julia_Variable var;
	public @S(20) Julia_PunctuationChoice equals = new Julia_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Julia_Expression value;
	public @S(40) Julia_EOLN eoln;
}
