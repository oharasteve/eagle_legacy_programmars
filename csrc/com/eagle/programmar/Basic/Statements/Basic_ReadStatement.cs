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
	using EagleInteger = com.eagle.math.EagleInteger;
	using Basic_StateMachine = com.eagle.programmar.Basic.Basic_StateMachine;
	using Basic_Variable = com.eagle.programmar.Basic.Basic_Variable;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Basic_ReadStatement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword READ = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("READ");
		public Basic_Keyword READ = new Basic_Keyword("READ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Basic.Basic_Variable, com.eagle.tokens.punctuation.PunctuationComma> variables;
		public SeparatedList<Basic_Variable, PunctuationComma> variables;

		public override void interpret(EagleInterpreter interpreter)
		{
			Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
			for (int i = 0; i < variables.getPrimaryCount(); i++)
			{
				Basic_Variable var = variables.getPrimaryElement(i);
				int data = state.DataValue;
				var.assignValue(interpreter, new EagleInteger(data));
			}
		}
	}

}
