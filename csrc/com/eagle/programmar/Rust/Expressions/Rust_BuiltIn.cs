// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_KeywordChoice = com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
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

	public class Rust_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice builtinConstant = new com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice("false", "true");
		public Rust_KeywordChoice builtinConstant = new Rust_KeywordChoice("false", "true");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtinConstant.ToString())
			{
			case "false":
				interpreter.pushBool(false);
				break;
			case "true":
				interpreter.pushBool(true);
				break;
			default:
				throw new Exception("Can't handle BuiltIn's other than true/false: " + builtinConstant);
			}
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

		public static Rust_BuiltIn generateBuiltIn(EagleGenerator.BuiltInEnum builtin, AbstractToken source)
		{
			Rust_BuiltIn built = new Rust_BuiltIn();
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
			return built;
		}
	}

}
