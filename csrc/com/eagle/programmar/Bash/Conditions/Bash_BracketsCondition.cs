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
	using Bash_Condition = com.eagle.programmar.Bash.Bash_Condition;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_BracketsCondition : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation leftBrackets = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("[[");
		public Bash_Punctuation leftBrackets = new Bash_Punctuation("[[");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Condition condition;
		public Bash_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Terminals.Bash_Punctuation rightBrackets = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("]]");
		public Bash_Punctuation rightBrackets = new Bash_Punctuation("]]");

		public override void interpret(EagleInterpreter interpreter)
		{
			bool cond = interpreter.getBoolValue(condition);
			interpreter.pushBool(cond);
		}
	}

}
