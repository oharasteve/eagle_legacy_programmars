// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class MSSolution_Header extends TokenSequence
{
	public @S(10) MSSolution_Keyword MICROSOFT = new MSSolution_Keyword("Microsoft");
	public @S(20) MSSolution_Keyword VISUAL = new MSSolution_Keyword("Visual");
	public @S(30) MSSolution_Keyword STUDIO = new MSSolution_Keyword("Studio");
	public @S(40) MSSolution_Keyword SOLUTION = new MSSolution_Keyword("Solution");
	public @S(50) MSSolution_Keyword FILE = new MSSolution_Keyword("File");
	public @S(60) PunctuationComma comma;
	public @S(70) MSSolution_Keyword FORMAT = new MSSolution_Keyword("Format");
	public @S(80) MSSolution_Keyword VERSION = new MSSolution_Keyword("Version");
	public @S(90) MSSolution_Number version;
	public @S(100) MSSolution_EndOfLine eoln;
}
