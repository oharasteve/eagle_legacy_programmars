// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Label;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class IBMASM_Address extends TokenSequence
{
	public @S(10) IBMASM_Label_or_Star label;
	public @S(20) @OPT IBMASM_AddressOffset offset;
	public @S(30) @OPT IBMASM_AddressSize size;

	public static class IBMASM_Label_or_Star extends TokenChooser
	{
		public @CHOICE IBMASM_Label label;
		public @CHOICE IBMASM_Number number;
		public @CHOICE PunctuationStar star;

		public @CHOICE static class IBMASM_Address_Equals extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) IBMASM_Keyword X = new IBMASM_Keyword("X");
			public @S(30) IBMASM_Literal literal;
		}
	}

	public static class IBMASM_AddressOffset extends TokenSequence
	{
		public @S(10) IBMASM_Punctuation plus = new IBMASM_Punctuation('+');
		public @S(20) IBMASM_Number offset;
	}

	public static class IBMASM_AddressSize extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) IBMASM_Number size;
		public @S(30) PunctuationRightParen rightParen;
	}
}
