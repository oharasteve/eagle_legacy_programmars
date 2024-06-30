// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_NextStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#Next-Statement") AWK_Keyword NEXT = new AWK_Keyword("next");
}
