// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Basic_StateMachine = com.eagle.programmar.Basic.Basic_StateMachine;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Basic_GoToStatement : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword GOTO = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("GOTO");
		public Basic_Keyword GOTO = new Basic_Keyword("GOTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Terminals.Basic_Number lbl;
		public Basic_Number lbl;

		public override void interpret(EagleInterpreter interpreter)
		{
			Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
			int label = int.Parse(lbl.getValue());
			state.gotoStatement(label); // Goto this label. No Deposit. No Return.
		}
	}

}
