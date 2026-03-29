// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice builtinConstant = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("FALSE", "TRUE");
		public Algol68_KeywordChoice builtinConstant = new Algol68_KeywordChoice("FALSE", "TRUE");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtinConstant.ToString())
			{
			case "FALSE":
				interpreter.pushBool(false);
				break;
			case "TRUE":
				interpreter.pushBool(true);
				break;
			default:
				throw new Exception("Can't handle BuiltIn's other than TRUE/FALSE: " + builtinConstant);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (builtinConstant.ToString().ToUpper())
			{
			case "FALSE":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			case "TRUE":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			default:
				throw new Exception("Can't handle BuiltIn: " + builtinConstant);
			}
		}
	}
}
