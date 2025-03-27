// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_SectionDirective extends TokenSequence
{
	public @S(10) IntelASM_KeywordChoice SECTION = new IntelASM_KeywordChoice("SECTION", "SECTION_TEXT");
	public @S(20) @OPT IntelASM_KeywordChoice DATA = new IntelASM_KeywordChoice(".data", ".rodata", ".text");
}
