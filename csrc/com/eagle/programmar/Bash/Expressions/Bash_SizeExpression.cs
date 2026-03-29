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
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_SizeExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation bang = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("#");
		public Bash_Punctuation bang = new Bash_Punctuation("#");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Variable var;
		public Bash_Variable var;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.findSymbol(var.id.getValue());
			if (val.isString())
			{
				string str = val.forceStringValue();
				interpreter.pushInt(str.Length);
				return;
			}
			throw new Exception("Unable to handle " + var);
		}
	}

}
