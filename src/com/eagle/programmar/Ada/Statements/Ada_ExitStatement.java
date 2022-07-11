// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_ExitStatement extends TokenSequence
{
	public @S(10) Ada_Keyword EXIT = new Ada_Keyword("exit");
	public @S(20) PunctuationSemicolon semicolon;
}
