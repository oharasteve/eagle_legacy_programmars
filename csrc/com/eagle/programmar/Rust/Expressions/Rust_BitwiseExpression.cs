// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
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

	public class Rust_BitwiseExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression left = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice operator = new com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice("&", "|", "^");
		public Rust_PunctuationChoice @operator = new Rust_PunctuationChoice("&", "|", "^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Expression right = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

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

		public static Rust_Expression generateBitwise(Rust_Expression leftExpr, EagleGenerator.BitwiseEnum oper, Rust_Expression rightExpr, AbstractToken source)
		{
			Rust_BitwiseExpression bit = new Rust_BitwiseExpression();
			bit.left = leftExpr;
			bit.right = rightExpr;
			switch (oper)
			{
			case AND:
				bit.@operator.setValue("&");
				break;
			case OR:
				bit.@operator.setValue("|");
				break;
			case XOR:
				bit.@operator.setValue("^");
				break;
			default:
				throw new Exception("Unable to handle " + oper);
			}
			bit.setTransformationSource(source);
			return Rust_Generator.wrapExpression(bit);
		}
	}

}
