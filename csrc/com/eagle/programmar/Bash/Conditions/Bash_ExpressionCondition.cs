// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Bash.Conditions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_ExpressionCondition : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Bash_Expression expression;
		public Bash_Expression expression;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(expression);
		}
	}

}
