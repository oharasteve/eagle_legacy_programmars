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
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using IntelASM_Label = com.eagle.programmar.IntelASM.IntelASM_Label;
	using IntelASM_Program = com.eagle.programmar.IntelASM.IntelASM_Program;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Label_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_CALL : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword CALL = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("CALL");
		public IntelASM_Keyword CALL = new IntelASM_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference label;
		public IntelASM_Label_Reference label;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			// Look up the label
			string name = label.getValue().ToUpper();

			interpreter.callingFunction(name, interpreter._lang);

			// Prepare to evaluate the method
			long startTime = System.nanoTime();
			state._calls.Push(Convert.ToInt32(state._nextInstruction));

			// And transfer control to the label
			state._nextInstruction = state._labels[name];
			IntelASM_Program lang = (IntelASM_Program) interpreter._lang;
			IntelASM_Label fn = (IntelASM_Label) lang.lines._elements.get(state._nextInstruction).getWhich();

			// There is no direct result from a CALL in assembler.
			// Usually stored in some register or another.
			long elapsedTime = System.nanoTime() - startTime;

			if (fn._callMetrics == null)
			{
				fn._callMetrics = new CallMetrics(interpreter._metrics, name, fn.label);
			}
			fn._callMetrics.addCallFrom(this, elapsedTime);

			// Now remove all those parameters
			interpreter.completedFunction(name, null);
		}
	}
}
