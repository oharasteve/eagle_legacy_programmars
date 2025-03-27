// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_EquDirective extends TokenSequence
{
	public @S(10) IntelASM_Label_Definition label;
	public @S(20) IntelASM_Keyword EQU = new IntelASM_Keyword("EQU");
	public @S(30) IntelASM_Expression expr;
}
