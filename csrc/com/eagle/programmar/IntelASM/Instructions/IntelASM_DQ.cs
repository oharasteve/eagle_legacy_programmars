// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

namespace com.eagle.programmar.IntelASM.Instructions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class IntelASM_DQ : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword DQ = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("DQ");
		public IntelASM_Keyword DQ = new IntelASM_Keyword("DQ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.IntelASM.IntelASM_Expression, com.eagle.tokens.punctuation.PunctuationComma> args;
		public SeparatedList<IntelASM_Expression, PunctuationComma> args;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			int numArgs = args.getPrimaryCount();
			for (int j = 0; j < numArgs; j++)
			{
				IntelASM_Expression expr = args.getPrimaryElement(j);
				int val = interpreter.getIntValue(expr);
				switch (state._section)
				{
				case IntelASM_StateMachine.IntelASM_Sections.RODATA:
				case IntelASM_StateMachine.IntelASM_Sections.DATA:
					state.setMemory4(state._memoryUsed, val);
					state._memoryUsed += 8; // Yeah, yeah, I know. Should be 4 for DW
					break;
				default:
					throw new Exception("DQ must be in .rodata or .data");
				}
			}
		}
	}

}
