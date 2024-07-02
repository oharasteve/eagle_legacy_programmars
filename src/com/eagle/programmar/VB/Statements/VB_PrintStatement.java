// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class VB_PrintStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) VB_Keyword PRINT = new VB_Keyword("print");
	public @S(20) VB_Punctuation pound = new VB_Punctuation('#');
	public @S(30) VB_Number channel;
	public @S(40) PunctuationComma comma;
	public @S(50) VB_Expression expr;
}
