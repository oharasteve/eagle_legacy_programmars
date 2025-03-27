// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_OpenStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) VB_Keyword OPEN = new VB_Keyword("open");
	public @S(20) VB_Expression fileName;
	public @S(30) VB_Keyword FOR = new VB_Keyword("for");
	public @S(40) VB_KeywordChoice inputOutput = new VB_KeywordChoice("input", "output");
	public @S(50) VB_Keyword AS = new VB_Keyword("as");
	public @S(60) VB_Punctuation pound = new VB_Punctuation('#');
	public @S(70) VB_Number channel;
}
