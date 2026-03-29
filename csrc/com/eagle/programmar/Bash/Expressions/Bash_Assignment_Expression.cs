// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Bash.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Bash_Assignment_Expression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Bash_Variable left;
		public Bash_Variable left;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice equals = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("=");
		public Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_Expression right = new com.eagle.programmar.Bash.Bash_Expression(this, AllowedPrecedence.HIGHER);
		public Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(right.getWhich() is Bash_Expression))
			{
				throw new Exception("Unexpected assignment expression: " + right.getWhich());
			}
			Bash_Expression expr = (Bash_Expression) right.getWhich();

			switch (equals.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(left, left.id.getValue(), val);
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + equals.getValue());
			}
		}
	}

}
