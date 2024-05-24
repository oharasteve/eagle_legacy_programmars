// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Address;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Format_S extends TokenSequence
{
	public @S(10) IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice("B", "BE", "BNE", "BNH", "BNL", "NOP", "STCK");

	public @S(20) IBMASM_Spaces spaces;
	public @S(30) IBMASM_Address address;
}
