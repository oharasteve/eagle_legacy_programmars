// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Julia_BreakStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("base/base/#break") Julia_Keyword BREAK = new Julia_Keyword("break");
	public @S(20) Julia_EOLN eoln;
}
