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
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_RelationalOperator = com.eagle.programmar.COBOL.COBOL_RelationalOperator;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_RelationCondition : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression left = new com.eagle.programmar.COBOL.COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_RelationalOperator operator;
		public COBOL_RelationalOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.COBOL_Expression right = new com.eagle.programmar.COBOL.COBOL_Expression(this, AllowedPrecedence.HIGHER);
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.canonicalForm(); // Returns "<", "=", etc.

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, @operator.getWhich(), oper);
			}
			_metrics.operated(leftValue.getType(), rightValue.getType());

			bool not = NOT.isPresent();
			bool result;

			if (leftValue.isString() || rightValue.isString())
			{
				string leftStr = interpreter.getStrValue(left);
				string rightStr = interpreter.getStrValue(right);
				switch (oper)
				{
				case "=":
					result = leftStr.Equals(rightStr);
					break;
				default:
					throw new Exception("Unable to handle " + oper + " for strings");
				}
			}
			else
			{
				int leftInt = interpreter.getIntValue(left);
				int rightInt = interpreter.getIntValue(right);
				switch (oper)
				{
				case "=":
					result = leftInt == rightInt;
					break;
				case "<>":
					result = leftInt != rightInt;
					break;
				case "<":
					result = leftInt < rightInt;
					break;
				case "<=":
					result = leftInt <= rightInt;
					break;
				case ">":
					result = leftInt > rightInt;
					break;
				case ">=":
					result = leftInt >= rightInt;
					break;
				default:
					throw new Exception("Unable to handle " + oper + " for integers");
				}
			}

			if (not)
			{
				result = !result;
			}
			interpreter.pushBool(result);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);

			string oper = @operator.canonicalForm(); // Returns "<", "=", etc.
			EagleGenerator.RelationalEnum newOper;

			if (NOT.isPresent())
			{
				switch (oper)
				{
				case "=":
					newOper = EagleGenerator.RelationalEnum.NOT_EQUALS;
					break;
				case "<>":
					newOper = EagleGenerator.RelationalEnum.EQUALS;
					break;
				case "<":
					newOper = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					break;
				case "<=":
					newOper = EagleGenerator.RelationalEnum.GREATER_THAN;
					break;
				case ">":
					newOper = EagleGenerator.RelationalEnum.LESS_EQUALS;
					break;
				case ">=":
					newOper = EagleGenerator.RelationalEnum.LESS_THAN;
					break;
				default:
					throw new Exception("Unexpected relational operator: NOT " + oper);
				}
			}
			else
			{
				switch (oper)
				{
				case "=":
					newOper = EagleGenerator.RelationalEnum.EQUALS;
					break;
				case "<>":
					newOper = EagleGenerator.RelationalEnum.NOT_EQUALS;
					break;
				case "<":
					newOper = EagleGenerator.RelationalEnum.LESS_THAN;
					break;
				case "<=":
					newOper = EagleGenerator.RelationalEnum.LESS_EQUALS;
					break;
				case ">":
					newOper = EagleGenerator.RelationalEnum.GREATER_THAN;
					break;
				case ">=":
					newOper = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					break;
				default:
					throw new Exception("Unexpected relational operator: " + oper);
				}
			}
			return generator.newRelationalExpression(types, leftExpr, newOper, rightExpr, this);
		}
	}

}
