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
	using IntelASM_Register = com.eagle.programmar.IntelASM.IntelASM_Register;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_OneArg : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice CMD = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("DEC", "DIV", "INC", "MUL", "NEG", "POP", "PUSH", "REP", "REPZ", "SETZ");
		public IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice("DEC", "DIV", "INC", "MUL", "NEG", "POP", "PUSH", "REP", "REPZ", "SETZ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.IntelASM_Register reg;
		public IntelASM_Register reg;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			switch (CMD.ToString().ToUpper())
			{
			case "DEC":
				int val1 = reg.getValue(state);
				reg.setValue(state, val1 - 1);
				break;
			case "DIV":
				// Divide RAX by reg, result in RAX, remainder in RDX
				int divisor = reg.getValue(state);
				int quotient = state._RAX / divisor;
				int remainder = state._RAX % divisor;
				// System.out.println("*** " + state._RAX + " / " + divisor + " = " + quotient +
				// " rem " + remainder);
				state._RAX = quotient;
				state._RDX = remainder;
				break;
			case "INC":
				int val2 = reg.getValue(state);
				reg.setValue(state, val2 + 1);
				break;
			case "MUL":
				int mult = reg.getValue(state);
				state._RAX = state._RAX * mult;
				break;
			default:
				throw new Exception("Unable to run command: " + CMD);
			}
		}
	}

}
