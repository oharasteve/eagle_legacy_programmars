// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Ruby_ReturnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Ruby_Keyword RETURN = new Ruby_Keyword("return");
	public @S(20) Ruby_Expression expr;
	public @S(30) Ruby_EOLN eoln;
}
