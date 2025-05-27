// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Variable;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class VB_SetStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("statements/set-statement") VB_Keyword SET = new VB_Keyword("set");
	public @S(20) VB_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) VB_Expression expr;
}
