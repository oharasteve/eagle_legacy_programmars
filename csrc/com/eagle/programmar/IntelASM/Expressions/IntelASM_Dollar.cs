// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

namespace com.eagle.programmar.IntelASM.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Punctuation = com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class IntelASM_Dollar : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation dollar = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation('$');
		public IntelASM_Punctuation dollar = new IntelASM_Punctuation('$');

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
			interpreter.pushInt(state._dollarLine);
		}
	}

}
