// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_REPNE extends TokenSequence
{
	public @S(10) IntelASM_Keyword REPNE = new IntelASM_Keyword("REPNE");
	public @S(20) IntelASM_Keyword SCASB = new IntelASM_Keyword("SCASB");
}