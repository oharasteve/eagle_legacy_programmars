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
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_NoArgs : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice CMD = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("CLD", "LODSB", "MOVSB", "NOP", "RET", "STD", "STOSB");
		public IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice("CLD", "LODSB", "MOVSB", "NOP", "RET", "STD", "STOSB");

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			switch (CMD.ToString().ToUpper())
			{
			case "RET":
				state._nextInstruction = state._calls.Pop().intValue();
				break;
			default:
				throw new Exception("Unable to run command: " + CMD);
			}
		}
	}

}
