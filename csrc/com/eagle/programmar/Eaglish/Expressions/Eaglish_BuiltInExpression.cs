// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 25, 2024

namespace com.eagle.programmar.Eaglish.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eaglish_KeywordChoice = com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_BuiltInExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice builtIns = new com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice("TRUE", "FALSE");
		public Eaglish_KeywordChoice builtIns = new Eaglish_KeywordChoice("TRUE", "FALSE");

		public override void interpret(EagleInterpreter interpreter)
		{
			string builtIn = builtIns.getValue();
			switch (builtIn)
			{
			case "TRUE":
				interpreter.pushBool(true);
				break;
			case "FALSE":
				interpreter.pushBool(false);
				break;
			default:
				throw new Exception("Unable to handle " + builtIn);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.BuiltInEnum builtIn;
			switch (builtIns.ToString())
			{
			case "TRUE":
				builtIn = EagleGenerator.BuiltInEnum.TRUE;
				break;
			case "FALSE":
				builtIn = EagleGenerator.BuiltInEnum.FALSE;
				break;
			default:
				throw new Exception("Unable to handle: " + builtIns);
			}
			return generator.newBuiltInExpression(builtIn, this);
		}
	}

}
