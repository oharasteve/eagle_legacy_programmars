// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Basic_Variable = com.eagle.programmar.Basic.Basic_Variable;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Basic_VariableExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Basic_Variable variable;
		public Basic_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}
	}

}
