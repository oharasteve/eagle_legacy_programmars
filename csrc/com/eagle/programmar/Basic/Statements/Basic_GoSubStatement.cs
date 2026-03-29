// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using Basic_StateMachine = com.eagle.programmar.Basic.Basic_StateMachine;
	using Basic_Statement = com.eagle.programmar.Basic.Basic_Statement;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Basic_GoSubStatement : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword GOSUB = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("GOSUB");
		public Basic_Keyword GOSUB = new Basic_Keyword("GOSUB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Terminals.Basic_Number lbl;
		public Basic_Number lbl;

		public override void interpret(EagleInterpreter interpreter)
		{
			Basic_StateMachine state = (Basic_StateMachine) interpreter._state;

			int label = int.Parse(lbl.getValue());
			int save = state.CurrentStatement;
			state.gotoStatement(label); // Gosub this label

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				Basic_Statement stmt = state.nextStatement();
				if (stmt == null)
				{
					break;
				}

				// Recursive ... goes away for a while
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			if (result == Eagle_Statement_Result.RETURN)
			{
				state.CurrentStatement = save;
			}
		}
	}

}
