// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine.IntelASM_Sections;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_SectionDirective extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_KeywordChoice SECTION = new IntelASM_KeywordChoice("SECTION", "SECTION_TEXT");
	public @S(20) @OPT IntelASM_KeywordChoice DOTDATA = new IntelASM_KeywordChoice(".data", ".rodata", ".text");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_Sections sect;
		switch (DOTDATA.toString().toLowerCase())
		{
		case ".rodata":
			sect = IntelASM_Sections.RODATA;
			break;
		case ".data":
			sect = IntelASM_Sections.DATA;
			break;
		case ".text":
			sect = IntelASM_Sections.TEXT;
			break;
		default:
			throw new RuntimeException("Unexpected Section: " + DOTDATA.toString());
		}
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		state._section = sect;
	}
}
