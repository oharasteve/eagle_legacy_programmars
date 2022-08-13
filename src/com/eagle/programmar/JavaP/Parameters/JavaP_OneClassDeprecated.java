// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_OneClassDeprecated extends TokenSequence
{
	public @S(10) JavaP_Keyword DEPRECATED = new JavaP_Keyword("Deprecated");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_Keyword TRUE = new JavaP_Keyword("true");
	public @S(40) JavaP_EndOfLine eoln;
}