// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.CMacro.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_PunctuationChoice = com.eagle.programmar.CMacro.Terminals.CMacro_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class CMacro_RelationalExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.CMacro_Expression left = new com.eagle.programmar.CMacro.CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_PunctuationChoice operator = new com.eagle.programmar.CMacro.Terminals.CMacro_PunctuationChoice("<", ">", "<=", ">=");
		public CMacro_PunctuationChoice @operator = new CMacro_PunctuationChoice("<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.CMacro_Expression right = new com.eagle.programmar.CMacro.CMacro_Expression(this, AllowedPrecedence.HIGHER);
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			string oper = @operator.getValue();
			if (oper.Equals("<"))
			{
				interpreter.pushBool(leftVal < rightVal);
			}
			else if (oper.Equals(">"))
			{
				interpreter.pushBool(leftVal > rightVal);
			}
			else if (oper.Equals("<="))
			{
				interpreter.pushBool(leftVal >= rightVal);
			}
			else if (oper.Equals(">="))
			{
				interpreter.pushBool(leftVal >= rightVal);
			}
			else
			{
				throw new Exception("Unexpected operator: " + oper);
			}
		}
	}

}
