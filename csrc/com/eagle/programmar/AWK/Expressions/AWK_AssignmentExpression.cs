// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_PunctuationChoice = com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class AWK_AssignmentExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Expression var = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Expression var = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice equals = new com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
		public AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression expr = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.HIGHER);
		public AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is AWK_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}

			AWK_VariableExpression varExpr = (AWK_VariableExpression) var.getWhich();
			switch (equals.getValue())
			{
			case "=":
				int x = interpreter.getIntValue(expr);
				EagleInteger val = new EagleInteger(x);
				interpreter.setSymbol(var, varExpr.variable.id.getValue(), val);
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + equals.getValue());
			}
		}
	}

}
