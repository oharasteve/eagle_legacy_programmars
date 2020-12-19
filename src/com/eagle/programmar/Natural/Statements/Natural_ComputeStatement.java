// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Natural_ComputeStatement extends TokenSequence
{
	public @S(10) @DOC("sm/compute.htm") Natural_Keyword COMPUTE = new Natural_Keyword("COMPUTE");
	public @S(20) Natural_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Natural_Expression expr;
}
