// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Symbols.Gupta_Data_Definition;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Gupta_Variable_Declaration extends TokenSequence
{
	public @S(10) Gupta_Type type;
	public @S(20) PunctuationColon colon;
	public @S(30) Gupta_Data_Definition varName;
	public @S(40) @OPT Gupta_InitialValue initValue;
	
	public static class Gupta_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Gupta_Literal literal;
	}
}
