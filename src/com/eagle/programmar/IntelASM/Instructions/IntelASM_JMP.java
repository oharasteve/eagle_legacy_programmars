// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_JMP extends TokenSequence
{
	public @S(10) IntelASM_KeywordChoice JMP = new IntelASM_KeywordChoice(
			"JC", "JE", "JG", "JGE", "JL", "JLE",
			"JMP", "JNE", "JNZ", "JZ");
	public @S(20) IntelASM_Label_Reference label;
}