// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

namespace com.eagle.programmar.CMD.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Variable = com.eagle.programmar.CMD.CMD_Variable;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class CMD_VariableExpression : PrimaryOperator, EagleRunnable
	{
		// Because CMD_Variable is not a TerminalToken, it has to be wrapped in a
		// PrimaryOperator
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.CMD_Variable variable;
		public CMD_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}
	}

}
