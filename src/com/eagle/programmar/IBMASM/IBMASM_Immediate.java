// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 13, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Immediate extends TokenChooser
{
	public @CHOICE IBMASM_Number XXnumber;
	public @CHOICE IBMASM_Address XXaddress;

	public @CHOICE static class IBMASM_Immediate_Data extends TokenSequence
	{
		public @S(10) IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("C", "X");
		public @S(20) IBMASM_Literal literal;
	}
}
