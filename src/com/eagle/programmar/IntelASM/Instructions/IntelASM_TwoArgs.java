// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_TwoArgs extends TokenSequence
{
	public @S(10) IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice(
			"ADD", "AND", "CMP", "LEA", "MOV", "MOVSX", "MOVZX",
			"OR", "SHL", "SHR", "SUB", "TEST", "XOR");
	public @S(20) IntelASM_Expression arg1;
	public @S(30) PunctuationComma comma;
	public @S(40) IntelASM_Expression arg2;
}