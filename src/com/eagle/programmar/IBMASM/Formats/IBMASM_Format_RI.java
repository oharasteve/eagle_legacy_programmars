// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Formats;

import com.eagle.programmar.IBMASM.IBMASM_Immediate;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IBMASM_Format_RI extends TokenSequence
{
	public @S(10) IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice("SLA", // Shift left A/L
			"SLL", // Shift left A/L
			"SLDA", // Shift left double A/L
			"SLDL", // Shift left double A/L
			"SRA", // Shift right A/L
			"SRL", // Shift right A/L
			"SRDA", // Shift right double A/L
			"SRDL" // Shift right double A/L
	);

	public @S(20) IBMASM_Spaces spaces;
	public @S(30) IBMASM_Register register;
	public @S(40) PunctuationComma comma;
	public @S(50) IBMASM_Immediate immediate;
}
