// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Variable = com.eagle.programmar.Powershell.Powershell_Variable;
	using Powershell_PunctuationChoice = com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
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

	public class Powershell_PostIncrementExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Variable var;
		public Powershell_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice operator = new com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice("++", "--");
		public Powershell_PunctuationChoice @operator = new Powershell_PunctuationChoice("++", "--");

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.findSymbol(var.id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr;
			switch (@operator.getValue())
			{
			case "++":
				curr = new EagleInteger(prev + 1);
				break;
			case "--":
				curr = new EagleInteger(prev - 1);
				break;
			default:
				throw new Exception("Unable to handle: " + @operator);
			}
			interpreter.setSymbol(var, var.id.getValue(), curr);
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

			string newName = Powershell_Variable.repairName(var.id.getValue());
			return generator.newPostIncrementExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
		}
	}

}
