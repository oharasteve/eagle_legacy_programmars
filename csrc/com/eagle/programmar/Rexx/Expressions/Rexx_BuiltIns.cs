// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

// NOTE: 'true' & 'false' are NOT part of the Rexx language!
// They are my little extensions that hopefully won't break anything.

namespace com.eagle.programmar.Rexx.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rexx_KeywordChoice = com.eagle.programmar.Rexx.Terminals.Rexx_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_BuiltIns : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Terminals.Rexx_KeywordChoice builtinConstant = new com.eagle.programmar.Rexx.Terminals.Rexx_KeywordChoice("false", "true");
		public Rexx_KeywordChoice builtinConstant = new Rexx_KeywordChoice("false", "true");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtinConstant.ToString().ToLower())
			{
			case "false":
				interpreter.pushBool(false);
				break;
			case "true":
				interpreter.pushBool(true);
				break;
			default:
				throw new Exception("Can't handle BuiltIn: " + builtinConstant);
			}
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
