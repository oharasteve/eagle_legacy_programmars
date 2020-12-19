// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeExceptions extends TokenSequence
{
	public @S(10) JavaP_Keyword EXCEPTION = new JavaP_Keyword("Exception");
	public @S(20) JavaP_Keyword TABLE = new JavaP_Keyword("table");
	public @S(30) PunctuationColon colon;
	public @S(40) JavaP_EndOfLine eoln1;
	
	public @S(50) JavaP_Keyword FROM = new JavaP_Keyword("from");
	public @S(60) JavaP_Keyword TO = new JavaP_Keyword("to");
	public @S(70) JavaP_Keyword TARGET = new JavaP_Keyword("target");
	public @S(80) JavaP_Keyword TYPE = new JavaP_Keyword("type");
	public @S(90) JavaP_EndOfLine eoln2;

	public @S(100) @OPT TokenList<JavaP_CodeExceptionEntry> entries;
	
	public static class JavaP_CodeExceptionEntry extends TokenSequence
	{
		public @S(10) JavaP_Number from;
		public @S(20) JavaP_Number to;
		public @S(30) JavaP_Number target;
		public @S(40) @OPT JavaP_Keyword CLASS = new JavaP_Keyword("Class");
		public @S(50) JavaP_QualifiedName name;
		public @S(60) JavaP_EndOfLine eoln;
	}
}
