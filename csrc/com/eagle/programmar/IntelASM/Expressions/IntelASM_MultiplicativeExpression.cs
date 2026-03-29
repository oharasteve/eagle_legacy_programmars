// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

namespace com.eagle.programmar.IntelASM.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_Expression = com.eagle.programmar.IntelASM.IntelASM_Expression;
	using IntelASM_PunctuationChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class IntelASM_MultiplicativeExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.IntelASM_Expression left = new com.eagle.programmar.IntelASM.IntelASM_Expression(this, AllowedPrecedence.ATLEAST);
		public IntelASM_Expression left = new IntelASM_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_PunctuationChoice operator = new com.eagle.programmar.IntelASM.Terminals.IntelASM_PunctuationChoice("*");
		public IntelASM_PunctuationChoice @operator = new IntelASM_PunctuationChoice("*");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IntelASM.IntelASM_Expression right = new com.eagle.programmar.IntelASM.IntelASM_Expression(this, AllowedPrecedence.HIGHER);
		public IntelASM_Expression right = new IntelASM_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			switch (@operator.ToString())
			{
			case "*":
				// System.out.println("****** " + leftInt + " * " + rightInt + " ******");
				interpreter.pushInt(leftInt * rightInt);
				break;
			default:
				throw new Exception("Unexpected multiplicative operator: " + @operator);
			}
		}
	}

}
