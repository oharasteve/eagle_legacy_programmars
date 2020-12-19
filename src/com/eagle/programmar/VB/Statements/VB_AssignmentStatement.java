// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class VB_AssignmentStatement extends TokenSequence
{
	public @S(10) VB_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) VB_Expression expr;
}
