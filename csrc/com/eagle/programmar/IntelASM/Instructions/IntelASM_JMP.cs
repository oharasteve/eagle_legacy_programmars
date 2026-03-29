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
	using JumpMetrics = com.eagle.metrics.JumpMetrics;
	using IntelASM_Label = com.eagle.programmar.IntelASM.IntelASM_Label;
	using IntelASM_Program = com.eagle.programmar.IntelASM.IntelASM_Program;
	using IntelASM_Line = com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Label_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_JMP : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice JMP = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("JC", "JE", "JG", "JGE", "JL", "JLE", "JMP", "JNE", "JNZ", "JZ");
		public IntelASM_KeywordChoice JMP = new IntelASM_KeywordChoice("JC", "JE", "JG", "JGE", "JL", "JLE", "JMP", "JNE", "JNZ", "JZ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference label;
		public IntelASM_Label_Reference label;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			bool jump;
			switch (JMP.getValue().ToUpper())
			{
			case "JMP":
				jump = true;
				break;
			case "JL":
				jump = state._flag < 0;
				break;
			case "JLE":
				jump = state._flag <= 0;
				break;
			case "JNE":
				jump = state._flag != 0;
				break;
			case "JE":
				jump = state._flag == 0;
				break;
			case "JGE":
				jump = state._flag >= 0;
				break;
			case "JG":
				jump = state._flag > 0;
				break;
			default:
				throw new Exception("Unable to handle " + JMP.getValue() + " yet.");
			}

			if (jump)
			{
				// Look up the label
				string name = label.getValue().ToUpper();

				// And transfer control to the label
				state._nextInstruction = state._labels[name];

				// Update metrics
				IntelASM_Program lang = (IntelASM_Program) interpreter._lang;
				IntelASM_Program.IntelASM_Line line = lang.lines._elements.get(state._nextInstruction);
				IntelASM_Label fn = (IntelASM_Label) line.getWhich();

				if (fn._jumpMetrics == null)
				{
					fn._jumpMetrics = new JumpMetrics(interpreter._metrics, name, fn);
				}
				fn._jumpMetrics.addJumpFrom(this);
			}
		}
	}

}
