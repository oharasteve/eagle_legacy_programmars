// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 13, 2022

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.Symbols.CSharp_Label_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CSharp_GotoStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) CSharp_Keyword GOTO = new CSharp_Keyword("goto");
	public @S(20) CSharp_Label_Reference label;
}
