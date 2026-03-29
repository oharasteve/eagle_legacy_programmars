// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_KeywordChoice = com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
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

	public class Python_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_KeywordChoice builtIn = new com.eagle.programmar.Python.Terminals.Python_KeywordChoice("None", "False", "True");
		public Python_KeywordChoice builtIn = new Python_KeywordChoice("None", "False", "True");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtIn.ToString())
			{
			case "False":
				interpreter.pushBool(false);
				break;
			case "True":
				interpreter.pushBool(true);
				break;
			default:
				throw new Exception("Can't handle BuiltIn's other than true/false: " + builtIn);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (builtIn.ToString())
			{
			case "False":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			case "True":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			default:
				throw new Exception("Can't handle BuiltIn: " + builtIn);
			}
		}

		public static Python_Expression generateBuiltIn(EagleGenerator.BuiltInEnum builtin, AbstractToken source)
		{
			Python_BuiltIn expr = new Python_BuiltIn();
			switch (builtin)
			{
			case TRUE:
				expr.builtIn = new Python_KeywordChoice("True");
				break;
			case FALSE:
				expr.builtIn = new Python_KeywordChoice("False");
				break;
			case NULL:
				expr.builtIn = new Python_KeywordChoice("None");
				break;
			default:
				throw new Exception("Unable to handle: " + builtin.ToString());
			}
			return Python_Generator.wrapExpression(expr);
		}
	}

}
