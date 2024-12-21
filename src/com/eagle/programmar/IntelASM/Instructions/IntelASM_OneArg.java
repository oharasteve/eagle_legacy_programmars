// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_OneArg extends TokenSequence
{
	public @S(10) IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice(
			"DEC", "DIV", "INC", "MUL", "NEG",
			"POP", "PUSH", "REP", "REPZ", "SETZ");
	public @S(20) IntelASM_Expression arg;
}
