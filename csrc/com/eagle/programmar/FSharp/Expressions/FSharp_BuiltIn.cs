// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.FSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using FSharp_KeywordChoice = com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice builtIn = new com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice("false", "true");
		public FSharp_KeywordChoice builtIn = new FSharp_KeywordChoice("false", "true");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtIn.ToString())
			{
			case "false":
				interpreter.pushBool(false);
				break;
			case "true":
				interpreter.pushBool(true);
				break;
			default:
				throw new Exception("Can't handle BuiltIn's other than true/false: " + builtIn);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.BuiltInEnum built;
			switch (builtIn.ToString())
			{
			case "false":
				built = EagleGenerator.BuiltInEnum.FALSE;
				break;
			case "true":
				built = EagleGenerator.BuiltInEnum.TRUE;
				break;
			default:
				throw new Exception("Unable to handle: " + builtIn);
			}
			return generator.newBuiltInExpression(built, this);
		}
	}

}
