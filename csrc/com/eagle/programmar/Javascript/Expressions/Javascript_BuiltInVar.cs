// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_BuiltInVar : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice builtinConstant = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice("arguments", "false", "null", "String", "super", "this", "true");
		public Javascript_KeywordChoice builtinConstant = new Javascript_KeywordChoice("arguments", "false", "null", "String", "super", "this", "true");

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
			switch (builtinConstant.ToString().ToLower())
			{
			case "false":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			case "true":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			default:
				throw new Exception("Can't handle BuiltIn: " + builtinConstant);
			}
		}
	}

}
