// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 23, 2020

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class JavaP_NestMembers extends TokenSequence
{
	public JavaP_Keyword NESTMEMBERS = new JavaP_Keyword("NestMembers");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln1;
	public SeparatedList<JavaP_Identifier, PunctuationSlash> className;
	public JavaP_Punctuation dollar = new JavaP_Punctuation('$');
	public JavaP_Identifier id;
	public JavaP_EndOfLine eoln2;
}
