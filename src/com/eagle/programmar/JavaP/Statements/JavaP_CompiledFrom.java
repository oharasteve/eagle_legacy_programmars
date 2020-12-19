// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Literal;
import com.eagle.tokens.TokenSequence;

public class JavaP_CompiledFrom extends TokenSequence
{
	public @S(10) JavaP_Keyword COMPILED = new JavaP_Keyword("Compiled");
	public @S(20) JavaP_Keyword FROM = new JavaP_Keyword("from");
	public @S(30) JavaP_Literal fileName;
	public @S(40) JavaP_EndOfLine eoln;
}
