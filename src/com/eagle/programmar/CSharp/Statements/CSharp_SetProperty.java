// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CSharp_SetProperty extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT CSharp_Keyword PRIVATE = new CSharp_Keyword("private");
	public @S(20) CSharp_Keyword SET = new CSharp_Keyword("set");
	public @S(30) @OPT CSharp_StatementBlock block;
}
