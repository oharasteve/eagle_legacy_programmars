// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Sections = com.eagle.programmar.IntelASM.IntelASM_StateMachine.IntelASM_Sections;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_SectionDirective : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice SECTION = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("SECTION", "SECTION_TEXT");
		public IntelASM_KeywordChoice SECTION = new IntelASM_KeywordChoice("SECTION", "SECTION_TEXT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT IntelASM_KeywordChoice DOTDATA = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice(".data", ".rodata", ".text");
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine.IntelASM_Sections sect;
			switch (DOTDATA.ToString().ToLower())
			{
			case ".rodata":
				sect = IntelASM_StateMachine.IntelASM_Sections.RODATA;
				break;
			case ".data":
				sect = IntelASM_StateMachine.IntelASM_Sections.DATA;
				break;
			case ".text":
				sect = IntelASM_StateMachine.IntelASM_Sections.TEXT;
				break;
			default:
				throw new Exception("Unexpected Section: " + DOTDATA.ToString());
			}
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
			state._section = sect;
		}
	}

}
