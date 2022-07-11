// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_Literal;
import com.eagle.tokens.TokenSequence;

public class Ada_PutStatement extends TokenSequence
{
	public @S(10) Ada_Keyword PUT = new Ada_Keyword("put");
	public @S(20) Ada_Literal literal;
}
