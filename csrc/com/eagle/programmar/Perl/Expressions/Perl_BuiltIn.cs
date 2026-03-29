// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice builtIn = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("FALSE", "False", "false", "TRUE", "True", "true", "NULL", "null", "T_CLASS", "T_FUNCTION", "T_INCLUDE", "T_INCLUDE_ONCE", "T_REQUIRE", "T_REQUIRE_ONCE", "T_USE", "namespace");
		public Perl_KeywordChoice builtIn = new Perl_KeywordChoice("FALSE", "False", "false", "TRUE", "True", "true", "NULL", "null", "T_CLASS", "T_FUNCTION", "T_INCLUDE", "T_INCLUDE_ONCE", "T_REQUIRE", "T_REQUIRE_ONCE", "T_USE", "namespace");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtIn.ToString().ToLower())
			{
			case "false":
				interpreter.pushBool(false);
				return;
			case "true":
				interpreter.pushBool(true);
				return;
			}
			throw new Exception("Can't handle BuiltIn's other than true/false: " + builtIn);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.BuiltInEnum built;
			switch (builtIn.ToString().ToLower())
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
