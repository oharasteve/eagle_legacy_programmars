// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_ReturnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Ada_Keyword RETURN = new Ada_Keyword("return");
	public @S(20) Ada_Expression expr;
	public @S(30) PunctuationSemicolon semicolon;
}
