// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.TokenSequence;

public class IntelASM_IfDefDirective extends TokenSequence
{
	public @S(10) IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
	public @S(20) IntelASM_KeywordChoice IFDEF = new IntelASM_KeywordChoice("ifdef", "ifndef");
	public @S(30) IntelASM_Identifier_Reference name;
}
