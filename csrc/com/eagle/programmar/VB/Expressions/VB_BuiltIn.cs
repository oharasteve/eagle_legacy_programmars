// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.VB.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_KeywordChoice builtIns = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("false", "true", "nothing");
		public VB_KeywordChoice builtIns = new VB_KeywordChoice("false", "true", "nothing");

		public override void interpret(EagleInterpreter interpreter)
		{
			string builtIn = builtIns.getValue();
			switch (builtIn)
			{
			case "true":
				interpreter.pushBool(true);
				break;
			case "false":
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
			case "false":
				builtIn = EagleGenerator.BuiltInEnum.FALSE;
				break;
			case "true":
				builtIn = EagleGenerator.BuiltInEnum.TRUE;
				break;
			case "nothing":
				builtIn = EagleGenerator.BuiltInEnum.NULL;
				break;
			default:
				throw new Exception("Unable to handle: " + builtIns);
			}
			return generator.newBuiltInExpression(builtIn, this);
		}
	}

}
