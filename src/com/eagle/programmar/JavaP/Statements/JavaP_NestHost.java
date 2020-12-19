// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 23, 2020

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class JavaP_NestHost extends TokenSequence
{
	public @S(10) JavaP_Keyword NESTHOST = new JavaP_Keyword("NestHost");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_Keyword CLASS = new JavaP_Keyword("class");
	public @S(40) SeparatedList<JavaP_Identifier, PunctuationSlash> className;
	public @S(50) JavaP_EndOfLine eoln;
}
