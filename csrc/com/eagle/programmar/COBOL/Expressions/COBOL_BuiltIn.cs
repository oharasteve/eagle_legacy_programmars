// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_BuiltIn : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice logicalConstant = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ANY", "FALSE", "HIGH-VALUES", "LINAGE-COUNTER", "LOW-VALUES", "QUOTE", "RETURN-CODE", "SPACE", "SPACES", "TRUE", "ZERO", "ZEROES", "ZEROS");
		public COBOL_KeywordChoice logicalConstant = new COBOL_KeywordChoice("ANY", "FALSE", "HIGH-VALUES", "LINAGE-COUNTER", "LOW-VALUES", "QUOTE", "RETURN-CODE", "SPACE", "SPACES", "TRUE", "ZERO", "ZEROES", "ZEROS");

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = logicalConstant.ToString().ToUpper();
			switch (name)
			{
			case "FALSE":
				interpreter.pushBool(false);
				break;
			case "TRUE":
				interpreter.pushBool(true);
				break;
			case "SPACES":
				interpreter.pushStr("");
				break;
			default:
				throw new Exception("Can't handle BuiltIn's other than TRUE/FALSE: " + name);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (logicalConstant.ToString().ToUpper())
			{
			case "FALSE":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			case "TRUE":
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			case "SPACES":
				return generator.newLiteralExpression("", this);
			default:
				throw new Exception("Can't handle BuiltIn: " + logicalConstant);
			}
		}
	}

}
