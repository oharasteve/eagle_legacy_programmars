// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class JavaP_OneClassExceptions extends TokenSequence
{
	public @S(10) JavaP_Keyword EXCEPTIONS = new JavaP_Keyword("Exceptions");
	public @S(20) PunctuationColon colon1;
	public @S(30) JavaP_EndOfLine eoln1;

	public @S(40) JavaP_Keyword THROWS = new JavaP_Keyword("throws");
	public @S(50) SeparatedList<JavaP_QualifiedName, PunctuationComma> name;
	public @S(60) @OPT JavaP_EndOfLine eoln2;
}