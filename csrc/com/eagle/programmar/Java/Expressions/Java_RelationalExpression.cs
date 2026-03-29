// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_EqualsMethod = com.eagle.programmar.Java.Methods.Java_EqualsMethod;
	using Java_Literal = com.eagle.programmar.Java.Terminals.Java_Literal;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_PunctuationChoice operator = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public Java_PunctuationChoice @operator = new Java_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression right = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

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

			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
			throw new Exception("Unexpected relational operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			switch (@operator.ToString())
			{
			case "==":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "!=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case "<":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "<=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case ">":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case ">=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			}
			throw new Exception("Unexpected relational operator: " + @operator);
		}

		private static bool isString(AbstractExpression expression)
		{
			Java_Expression expr = (Java_Expression) expression;
			if (expr.getWhich() is Java_Literal)
			{
				return true;
			}
			return false;
		}

		public static Java_Expression generateRelational(Operator2Metrics.Oper2Types types, Java_Expression leftExpr, EagleGenerator.RelationalEnum relOp, Java_Expression rightExpr, AbstractToken source)
		{
			Java_RelationalExpression relExp = new Java_RelationalExpression();
			bool doStrings = false;
			if (types != null)
			{
				if (types._type1 == EagleGenerator.TypeEnum.STRING && types._type2 == EagleGenerator.TypeEnum.STRING)
				{
					doStrings = true;
				}
			}

			if (!doStrings)
			{
				if (isString(leftExpr) || isString(rightExpr))
				{
					doStrings = true;
				}
			}

			if (doStrings)
			{
				Java_Expression parenExpr = Java_ParenthesizedExpression.generateParentheses(leftExpr, null);
				Java_EqualsMethod equals = Java_EqualsMethod.newEqualsMethod(parenExpr, rightExpr);
				equals.setTransformationSource(source);
				Java_Expression equalsExpr = Java_Generator.wrapExpression(equals);
				switch (relOp)
				{
				case EQUALS:
					return equalsExpr;
				case NOT_EQUALS:
					return Java_LogicalNotExpression.generateLogicalNot(equalsExpr, source);
				default:
					throw new Exception("Unable to handle " + relOp + " with strings");
				}
			}

			relExp.left = leftExpr;
			relExp.right = rightExpr;
			string oper;
			switch (relOp)
			{
			case EQUALS:
				oper = "==";
				break;
			case NOT_EQUALS:
				oper = "!=";
				break;
			case LESS_THAN:
				oper = "<";
				break;
			case LESS_EQUALS:
				oper = "<=";
				break;
			case GREATER_THAN:
				oper = ">";
				break;
			case GREATER_EQUALS:
				oper = ">=";
				break;
			default:
				throw new Exception("Unable to handle operator " + relOp);
			}
			relExp.@operator.setValue(oper);
			relExp.setTransformationSource(source);
			return Java_Generator.wrapExpression(relExp);
		}
	}

}
