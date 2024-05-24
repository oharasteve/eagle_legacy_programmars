// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2024

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_TypeList extends TokenSequence
{
	public @S(10) @OPT Python_EndOfLine eoln1;
	public @S(20) Python_Type type;
	public @S(30) @OPT TokenList<Python_MoreCommaList> moreTypes;
	public @S(40) @OPT Python_EndOfLine eoln2;

	public static class Python_MoreCommaList extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) Python_Type typ;
	}
}
