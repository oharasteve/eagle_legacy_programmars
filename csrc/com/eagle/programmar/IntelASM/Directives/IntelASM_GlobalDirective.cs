// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

namespace com.eagle.programmar.IntelASM.Directives
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_StateMachine = com.eagle.programmar.IntelASM.IntelASM_StateMachine;
	using IntelASM_Identifier_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_GlobalDirective : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword GLOBAL = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("GLOBAL");
		public IntelASM_Keyword GLOBAL = new IntelASM_Keyword("GLOBAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference id;
		public IntelASM_Identifier_Reference id;

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
			state._startLabel = id.getValue().ToUpper();
		}
	}

}
