// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Template.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Template_Expression = com.eagle.programmar.Template.Template_Expression;
	using Template_PunctuationChoice = com.eagle.programmar.Template.Terminals.Template_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Template_RelationalExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Template.Template_Expression left = new com.eagle.programmar.Template.Template_Expression(this, AllowedPrecedence.ATLEAST);
		public Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Template.Terminals.Template_PunctuationChoice operator = new com.eagle.programmar.Template.Terminals.Template_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public Template_PunctuationChoice @operator = new Template_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Template.Template_Expression right = new com.eagle.programmar.Template.Template_Expression(this, AllowedPrecedence.HIGHER);
		public Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			string oper = @operator.ToString();
			switch (oper)
			{
			case "<":
				interpreter.pushBool(leftValue < rightValue);
				break;
			case "<=":
				interpreter.pushBool(leftValue <= rightValue);
				break;
			case "==":
				interpreter.pushBool(leftValue == rightValue);
				break;
			case "!=":
				interpreter.pushBool(leftValue != rightValue);
				break;
			case ">=":
				interpreter.pushBool(leftValue >= rightValue);
				break;
			case ">":
				interpreter.pushBool(leftValue > rightValue);
				break;
			default:
				throw new Exception("Unexpected relational operator: " + @operator);
			}
		}
	}

}
