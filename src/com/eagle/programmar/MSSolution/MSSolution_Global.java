// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class MSSolution_Global extends TokenSequence
{
	public @S(10) MSSolution_Keyword GLOBAL = new MSSolution_Keyword("Global");
	public @S(20) MSSolution_EndOfLine eoln1;
	public @S(30) TokenList<MSSolution_GlobalSection> sections;
	public @S(40) MSSolution_Keyword ENDGLOBAL = new MSSolution_Keyword("EndGlobal");
	public @S(50) MSSolution_EndOfLine eoln2;
}
