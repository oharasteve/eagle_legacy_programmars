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
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_DollarPound : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice dollarPound = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("$#", "$?", "$@", "$*");
		public Bash_PunctuationChoice dollarPound = new Bash_PunctuationChoice("$#", "$?", "$@", "$*");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (dollarPound.getValue())
			{
			case "$?":
				EagleValue val = interpreter.findSymbol("$?");
				int code = val.forceIntegerValue();
				interpreter.pushInt(code);
				break;
			default:
				throw new Exception("Unable to handle variable: " + dollarPound.getValue());
			}
		}
	}

}
