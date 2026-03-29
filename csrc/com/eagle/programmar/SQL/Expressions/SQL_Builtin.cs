// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_Builtin : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice builtIn = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("FALSE", "NULL", "SYSTIMESTAMP", "TRUE");
		public SQL_KeywordChoice builtIn = new SQL_KeywordChoice("FALSE", "NULL", "SYSTIMESTAMP", "TRUE");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtIn.ToString().ToUpper())
			{
			case "FALSE":
				interpreter.pushBool(false);
				return;
			case "TRUE":
				interpreter.pushBool(true);
				return;
			}
			throw new Exception("Can't handle BuiltIn: " + builtIn);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.BuiltInEnum val;
			switch (builtIn.ToString().ToUpper())
			{
			case "FALSE":
				val = EagleGenerator.BuiltInEnum.FALSE;
				break;
			case "TRUE":
				val = EagleGenerator.BuiltInEnum.TRUE;
				break;
			default:
				throw new Exception("Unable to handle: " + builtIn);
			}
			return generator.newBuiltInExpression(val, this);
		}
	}

}
