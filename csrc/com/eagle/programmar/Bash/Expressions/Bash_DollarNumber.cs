// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Bash.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_DollarNumber : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation dollar = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("$");
		public Bash_Punctuation dollar = new Bash_Punctuation("$");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Number number;
		public Bash_Number number;

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = dollar.getValue() + number.ToString();
			EagleValue value = interpreter.findSymbol(name);
			interpreter.pushEagleValue(value);
		}
	}

}
