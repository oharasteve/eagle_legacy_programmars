// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 13, 2011

package com.eagle.programmar.IBMASM.Macros;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class IBMASM_WTO_Macro extends TokenSequence
{
	public @S(10) IBMASM_Spaces spaces1;
	public @S(20) IBMASM_Keyword WTO = new IBMASM_Keyword("WTO");
	public @S(30) IBMASM_Spaces spaces2;
	public @S(40) IBMASM_WTO_Value value;
	public @S(50) IBMASM_Spaces spaces3;
	public @S(60) @OPT IBMASM_Remark remark;

	public static class IBMASM_WTO_Value extends TokenChooser
	{
		public @CHOICE static class IBMASM_WTO_E extends TokenSequence
		{
			public @S(10) IBMASM_Keyword MF = new IBMASM_Keyword("MF");
			public @S(20) PunctuationEquals equals;
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) IBMASM_Keyword E = new IBMASM_Keyword("E");
			public @S(50) PunctuationComma comma;
			public @S(60) IBMASM_Address address;
			public @S(70) PunctuationRightParen rightParen;
		}
	}
}
