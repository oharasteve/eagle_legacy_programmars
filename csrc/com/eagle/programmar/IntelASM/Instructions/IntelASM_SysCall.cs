// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2025

namespace com.eagle.programmar.IntelASM.Instructions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_SysCall : TokenSequence, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword CMD = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("SYSCALL");
		public IntelASM_Keyword CMD = new IntelASM_Keyword("SYSCALL");

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			int rax = state._RAX;
			switch (rax)
			{
			case 1: // Print
				int stream = state._RDI;
				if (stream != 1)
				{
					throw new Exception("Can only print to stdout");
				}
				int pos = state._RSI;
				int nc = state._RDX;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < nc; i++)
				{
					sb.Append((char) state.getMemory1(pos + i));
				}
				Console.Write(sb.ToString()); // Should have its own line-feed
				break;
			case 60: // Exit
				interpreter._exitCode = state._RDI;
				return Eagle_Statement_Result.RETURN;
			default:
				throw new Exception("Unable to handle SYSCALL " + rax);
			}
			return Eagle_Statement_Result.NORMAL;
		}
	}

}
