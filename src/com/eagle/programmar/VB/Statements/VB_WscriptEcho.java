// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 22, 2012

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class VB_WscriptEcho extends TokenSequence
{
	public @S(10) VB_Keyword WSCRIPT = new VB_Keyword("wscript");
	public @S(20) PunctuationPeriod dot;
	public @S(30) VB_Keyword ECHO = new VB_Keyword("echo");
	public @S(40) VB_Expression expr;
}
