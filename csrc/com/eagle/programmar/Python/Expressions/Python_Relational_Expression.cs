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
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Relational_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_Relational_Operator operator;
		public Python_Relational_Operator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public class Python_IN_Operator : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_Keyword NOT = new com.eagle.programmar.Python.Terminals.Python_Keyword("not");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_Keyword IN = new com.eagle.programmar.Python.Terminals.Python_Keyword("in");
			public Python_Keyword IN = new Python_Keyword("in");
		}

		public class Python_IS_Operator : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword IS = new com.eagle.programmar.Python.Terminals.Python_Keyword("is");
			public Python_Keyword IS = new Python_Keyword("is");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Keyword NOT = new com.eagle.programmar.Python.Terminals.Python_Keyword("not");
			public  OPT;
		}

		public class Python_Relational_Operator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_PunctuationChoice XXoperatorSymbol = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("==", "!=", "<>", "<=", ">=", "<", ">");
			public Python_PunctuationChoice XXoperatorSymbol = new Python_PunctuationChoice("==", "!=", "<>", "<=", ">=", "<", ">");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_IN_Operator XXinOperator;
			public Python_IN_Operator XXinOperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_IS_Operator XXisOperator;
			public Python_IS_Operator XXisOperator;
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
				switch (oper)
				{
				case "==":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case "!=":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper)
				{
				case "==":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case "!=", "<>":
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
				default:
					throw new Exception("Unable to handle operator: " + oper);
				}
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator.getWhich());

			switch (@operator.getWhich().ToString())
			{
			case "==":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case "!=", "<>":
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
			throw new Exception("Unexpected relational operator: " + @operator.getWhich());
		}

		public static Python_Expression generateRelational(Operator2Metrics.Oper2Types types, Python_Expression leftExpr, EagleGenerator.RelationalEnum relOp, Python_Expression rightExpr, AbstractToken source)
		{
			Python_Relational_Expression relExp = new Python_Relational_Expression();
			relExp.left = leftExpr;
			relExp.right = rightExpr;

			Python_PunctuationChoice oper = null;
			switch (relOp)
			{
			case EQUALS:
				oper = new Python_PunctuationChoice("==");
				break;
			case NOT_EQUALS:
				oper = new Python_PunctuationChoice("!=");
				break;
			case LESS_THAN:
				oper = new Python_PunctuationChoice("<");
				break;
			case LESS_EQUALS:
				oper = new Python_PunctuationChoice("<=");
				break;
			case GREATER_THAN:
				oper = new Python_PunctuationChoice(">");
				break;
			case GREATER_EQUALS:
				oper = new Python_PunctuationChoice(">=");
				break;
			}
			relExp.@operator = new Python_Relational_Operator();
			relExp.@operator.setWhich(oper);
			relExp.setTransformationSource(source);
			return Python_Generator.wrapExpression(relExp);
		}
	}

}
