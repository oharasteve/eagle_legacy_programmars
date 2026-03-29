// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_KeywordChoice builtinConstant = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("false", "true", "null", "this", "super");
		public Java_KeywordChoice builtinConstant = new Java_KeywordChoice("false", "true", "null", "this", "super");

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
			throw new Exception("Can't handle BuiltIn: " + builtinConstant);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (builtinConstant.ToString().ToLower())
			{
			case "false":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			case "true":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			case "null":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.NULL, this);
			case "this":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.SELF, this);
			case "super":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.SUPER, this);
			default:
				throw new Exception("Can't handle BuiltIn: " + builtinConstant);
			}
		}

		public static Java_Expression generateBuiltIn(EagleGenerator.BuiltInEnum builtin, AbstractToken source)
		{
			Java_BuiltIn built = new Java_BuiltIn();
			switch (builtin)
			{
			case TRUE:
				built.builtinConstant.setValue("true");
				break;
			case FALSE:
				built.builtinConstant.setValue("false");
				break;
			default:
				throw new Exception("Unable to handle: " + builtin);
			}
			built.setTransformationSource(source);
			return Java_Generator.wrapExpression(built);
		}
	}

}
