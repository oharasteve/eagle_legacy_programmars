// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CMD_Pushd_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("pushd.mspx") CMD_Keyword PUSHD = new CMD_Keyword("pushd");
	public @S(20) CMD_Expression dir;
}
