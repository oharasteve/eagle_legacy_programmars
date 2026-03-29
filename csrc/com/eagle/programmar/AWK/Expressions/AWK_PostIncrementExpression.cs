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
	using EagleValue = com.eagle.math.EagleValue;
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_PunctuationChoice = com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using IncrementEnum = com.eagle.transform.EagleGenerator.IncrementEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_PostIncrementExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Variable var;
		public AWK_Variable var; // Cannot be just AWK_Expression -- infinite loop
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice operator = new com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice("++", "--");
		public AWK_PunctuationChoice @operator = new AWK_PunctuationChoice("++", "--");

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.findSymbol(var.id.getValue());
			int prev = val.forceIntegerValue();
			int curr;
			switch (@operator.getValue())
			{
			case "++":
				curr = prev + 1;
				break;
			case "--":
				curr = prev - 1;
				break;
			default:
				throw new Exception("Unexpected operator: " + @operator);
			}
			interpreter.setSymbol(var, var.id.getValue(), new EagleInteger(curr));
			interpreter.pushInt(prev);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.IncrementEnum whichDirection;
			switch (@operator.getValue())
			{
			case "++":
				whichDirection = EagleGenerator.IncrementEnum.INCREMENT;
				break;
			case "--":
				whichDirection = EagleGenerator.IncrementEnum.DECREMENT;
				break;
			default:
				throw new Exception("Unexpected operator: " + @operator);
			}
			return generator.newPostIncrementExpression(var.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
		}
	}

}
