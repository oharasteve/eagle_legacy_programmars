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
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BitwiseEnum = com.eagle.transform.EagleGenerator.BitwiseEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Bitwise_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_PunctuationChoice operator = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("&", "|", "^");
		public Python_PunctuationChoice @operator = new Python_PunctuationChoice("&", "|", "^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			switch (@operator.ToString())
			{
			case "&":
				interpreter.pushInt(leftValue & rightValue);
				break;
			case "|":
				interpreter.pushInt(leftValue | rightValue);
				break;
			case "^":
				interpreter.pushInt(leftValue ^ rightValue);
				break;
			default:
				throw new Exception("Unable to handle " + @operator);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			EagleGenerator.BitwiseEnum oper;
			switch (@operator.getValue())
			{
			case "&":
				oper = EagleGenerator.BitwiseEnum.AND;
				break;
			case "|":
				oper = EagleGenerator.BitwiseEnum.OR;
				break;
			case "^":
				oper = EagleGenerator.BitwiseEnum.XOR;
				break;
			default:
				throw new Exception("Unable to handle " + @operator);
			}
			return generator.newBitwiseExpression(leftExpr, oper, rightExpr, this);
		}

		public static Python_Expression generateBitwise(Python_Expression leftExpr, EagleGenerator.BitwiseEnum oper, Python_Expression rightExpr, AbstractToken source)
		{
			Python_Bitwise_Expression bitExpr = new Python_Bitwise_Expression();
			bitExpr.left = leftExpr;
			bitExpr.right = rightExpr;
			switch (oper)
			{
			case AND:
				bitExpr.@operator.setValue("&");
				break;
			case OR:
				bitExpr.@operator.setValue("|");
				break;
			case XOR:
				bitExpr.@operator.setValue("^");
				break;
			default:
				throw new Exception("Unable to handle " + oper);
			}
			bitExpr.setTransformationSource(source);
			return Python_Generator.wrapExpression(bitExpr);
		}
	}

}
