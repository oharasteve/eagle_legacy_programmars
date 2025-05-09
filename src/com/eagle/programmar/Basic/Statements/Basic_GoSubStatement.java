// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Basic_GoSubStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Basic_Keyword GOSUB = new Basic_Keyword("GOSUB");
	public @S(20) Basic_Number lbl;
}
