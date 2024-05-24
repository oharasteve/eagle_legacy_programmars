// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Assignment extends TokenSequence
{
	public @S(10) Ada_Variable var;
	public @S(20) Ada_PunctuationChoice equals = new Ada_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ada_Expression value;
	public @S(40) PunctuationSemicolon semicolon;
}
