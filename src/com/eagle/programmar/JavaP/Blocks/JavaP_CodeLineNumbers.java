// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeLineNumbers extends TokenSequence
{
	public @S(10) JavaP_Keyword LINENUMBER = new JavaP_Keyword("LineNumberTable");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln;
	public @S(40) TokenList<JavaP_CodeLineEntry> entries;
	
	public static class JavaP_CodeLineEntry extends TokenSequence
	{
		public @S(10) JavaP_Keyword LINE = new JavaP_Keyword("line");
		public @S(20) JavaP_Number number1;
		public @S(30) PunctuationColon colon;
		public @S(40) JavaP_Number number2;
		public @S(50) JavaP_EndOfLine eoln;
	}
}
