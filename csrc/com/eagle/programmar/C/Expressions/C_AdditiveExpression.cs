// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_AdditiveExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Expression left = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("+", "-");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression right = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Expression right = new C_Expression(this, AllowedPrecedence.HIGHER);

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.ToString();

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, @operator, oper);
			}
			_metrics.operated(leftValue.getType(), rightValue.getType());

			int rightInt = rightValue.forceIntegerValue();
			if (leftValue.isString())
			{
				string leftStr = leftValue.forceStringValue();
				switch (@operator.ToString())
				{
				case "+":
					interpreter.pushStr(leftStr.Substring(rightInt));
					break;
				default:
					throw new Exception("Unexpected string additive operator: " + oper);
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				switch (@operator.ToString())
				{
				case "+":
					interpreter.pushInt(leftInt + rightInt);
					break;
				case "-":
					interpreter.pushInt(leftInt - rightInt);
					break;
				default:
					throw new Exception("Unexpected numeric additive operator: " + oper);
				}
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			if (types != null)
			{
				if (types._type1 == EagleGenerator.TypeEnum.STRING && types._type2 == EagleGenerator.TypeEnum.INTEGER)
				{
					// str+sc in C means substring(str, sc)
					return generator.newSubstringFunction(leftExpr, rightExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_NEITHER, null, false, left);
				}
			}

			switch (@operator.ToString())
			{
			case "+":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.PLUS, rightExpr, this);
			case "-":
				return generator.newAdditiveExpression(types, leftExpr, EagleGenerator.AdditiveEnum.MINUS, rightExpr, this);
			default:
				throw new Exception("Unexpected additive operator: " + @operator);
			}
		}
	}

}
