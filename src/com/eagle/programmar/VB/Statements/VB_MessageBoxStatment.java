// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_MessageBoxStatment extends TokenSequence implements AbstractStatement
{
	public @S(10) VB_Keyword MESSAGEBOX = new VB_Keyword("MsgBox");
	public @S(20) VB_Expression expr;
}
