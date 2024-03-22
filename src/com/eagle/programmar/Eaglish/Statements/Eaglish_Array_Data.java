// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_Array_Data extends TokenSequence
{
	public @S(10) Eaglish_Keyword ARRAY = new Eaglish_Keyword("ARRAY");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_Array_InitialValues init;
	public @S(40) Eaglish_EndOfLine eoln;
	
	public static class Eaglish_Array_InitialValues extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> values;
	}
}
