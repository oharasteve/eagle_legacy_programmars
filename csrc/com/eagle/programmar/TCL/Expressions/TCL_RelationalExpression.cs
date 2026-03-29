// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.TCL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_KeywordChoice = com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
	using TCL_PunctuationChoice = com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.TCL.TCL_Expression left = new com.eagle.programmar.TCL.TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) TCL_RelOperator operator;
		public TCL_RelOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.TCL_Expression right = new com.eagle.programmar.TCL.TCL_Expression(this, AllowedPrecedence.HIGHER);
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

		public class TCL_RelOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_KeywordChoice XXEQ = new com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice("lt", "le", "eq", "ne", "gt", "ge");
			public TCL_KeywordChoice XXEQ = new TCL_KeywordChoice("lt", "le", "eq", "ne", "gt", "ge");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_PunctuationChoice XXoperator = new com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice("<", ">", "<=", ">=", "==", "<>", "!=");
			public TCL_PunctuationChoice XXoperator = new TCL_PunctuationChoice("<", ">", "<=", ">=", "==", "<>", "!=");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator2Metrics _metrics = null;
		private Operator2Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue leftValue = interpreter.getEagleValue(left);
			EagleValue rightValue = interpreter.getEagleValue(right);
			string oper = @operator.getWhich().ToString();

			if (_metrics == null)
			{
				_metrics = new Operator2Metrics(interpreter._metrics, @operator.getWhich(), oper);
			}
			_metrics.operated(leftValue.getType(), rightValue.getType());

			if (leftValue.isString() || rightValue.isString())
			{
				string leftStr = leftValue.forceStringValue();
				string rightStr = rightValue.forceStringValue();
				switch (oper.ToLower())
				{
				case "==", "eq":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "!=", "ne":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper.ToLower())
				{
				case "==", "eq":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case "<>", "!=", "ne":
					interpreter.pushBool(leftInt != rightInt);
					return;
				case "<", "lt":
					interpreter.pushBool(leftInt < rightInt);
					return;
				case "<=", "le":
					interpreter.pushBool(leftInt <= rightInt);
					return;
				case ">", "gt":
					interpreter.pushBool(leftInt > rightInt);
					return;
				case ">=", "ge":
					interpreter.pushBool(leftInt >= rightInt);
					return;
				}
			}

			throw new Exception("Unexpected relational operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator.getWhich());

			switch (@operator.getWhich().ToString())
			{
			case "==", "eq":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "<>", "!=", "ne":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			case "<", "lt":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "<=", "le":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case ">", "gt":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case ">=", "ge":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + @operator);
			}
		}
	}

}
