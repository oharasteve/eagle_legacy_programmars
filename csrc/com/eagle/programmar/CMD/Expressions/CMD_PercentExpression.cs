// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2024

namespace com.eagle.programmar.CMD.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class CMD_PercentExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation percent1 = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation("%");
		public CMD_Punctuation percent1 = new CMD_Punctuation("%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.CMD_Expression expr;
		public CMD_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Punctuation percent2 = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation("%");
		public CMD_Punctuation percent2 = new CMD_Punctuation("%");

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(expr);
		}
	}

}
