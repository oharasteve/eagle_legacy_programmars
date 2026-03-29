// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Scala.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Scala_KeywordChoice = com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice builtinConstant = new com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice("false", "true");
		public Scala_KeywordChoice builtinConstant = new Scala_KeywordChoice("false", "true");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtinConstant.ToString())
			{
			case "false":
				interpreter.pushBool(false);
				return;
			case "true":
				interpreter.pushBool(true);
				return;
			}
			throw new Exception("Can't handle BuiltIn's other than true/false: " + builtinConstant);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.BuiltInEnum builtIn;
			switch (builtinConstant.ToString())
			{
			case "false":
				builtIn = EagleGenerator.BuiltInEnum.FALSE;
				break;
			case "true":
				builtIn = EagleGenerator.BuiltInEnum.TRUE;
				break;
			default:
				throw new Exception("Unable to handle: " + builtinConstant);
			}
			return generator.newBuiltInExpression(builtIn, this);
		}
	}

}
