// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Define_Definition;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenSequence;

public class IntelASM_DefineDirective extends TokenSequence
{
	public @S(10) IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
	public @S(20) IntelASM_Keyword DEFINE = new IntelASM_Keyword("define");
	public @S(30) IntelASM_Define_Definition name;
	public @S(40) @OPT IntelASM_Identifier_Reference value;
}
