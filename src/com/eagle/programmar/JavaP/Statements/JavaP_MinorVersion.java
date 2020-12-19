// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 13, 2016

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_MinorVersion extends TokenSequence
{
	public @S(10) JavaP_Keyword MINOR = new JavaP_Keyword("minor");
	public @S(20) JavaP_Keyword VERSION = new JavaP_Keyword("version");
	public @S(30) PunctuationColon colon;
	public @S(40) JavaP_Number version;
	public @S(50) JavaP_EndOfLine eoln;
}
