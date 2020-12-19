// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_SourceFile extends TokenSequence
{
	public @S(10) JavaP_Keyword SOURCEFILE = new JavaP_Keyword("SourceFile");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_Literal fileName;
	public @S(40) JavaP_EndOfLine eoln;
}
