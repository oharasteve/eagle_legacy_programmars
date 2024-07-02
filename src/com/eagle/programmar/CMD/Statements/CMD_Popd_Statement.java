// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CMD_Popd_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("popd.mspx") CMD_Keyword POPD = new CMD_Keyword("popd");
}
