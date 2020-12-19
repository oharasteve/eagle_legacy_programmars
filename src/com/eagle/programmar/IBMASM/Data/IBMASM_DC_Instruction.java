// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Data;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class IBMASM_DC_Instruction extends TokenSequence
{
	public @S(10) @OPT IBMASM_Label_Definition label;
	public @S(20) IBMASM_Spaces spaces1;
	public @S(30) IBMASM_Keyword DC = new IBMASM_Keyword("DC");
	public @S(40) IBMASM_Spaces spaces2;
	public @S(50) IBMASM_DC_Value value;
	public @S(60) IBMASM_Spaces spaces3;
	public @S(70) @OPT IBMASM_Remark remark;

	public static class IBMASM_DC_Value extends TokenChooser
	{
		public @CHOICE static class IBMASM_DC_Address extends TokenSequence
		{
			public @S(10) IBMASM_Keyword A = new IBMASM_Keyword("A");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) IBMASM_Address address;
			public @S(40) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class IBMASM_DC_Length extends TokenSequence
		{
			public @S(10) @OPT IBMASM_Number replication;
			public @S(20) IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("CL", "XL");
			public @S(30) IBMASM_Number number;
			public @S(40) IBMASM_Literal literal;
		}
		
		public @CHOICE static class IBMASM_DC_Literal extends TokenSequence
		{
			public @S(10) IBMASM_KeywordChoice code = new IBMASM_KeywordChoice(
					"B", "C", "E", "F", "G", "H", "P", "X");
			public @S(20) IBMASM_Literal literal;
		}
	}
}
