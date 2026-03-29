// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.SQL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_PunctuationChoice = com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using MultiplicativeEnum = com.eagle.transform.EagleGenerator.MultiplicativeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_MultiplicativeExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.SQL_Expression left = new com.eagle.programmar.SQL.SQL_Expression(this, AllowedPrecedence.ATLEAST);
		public SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) SQL_MultOperator operator;
		public SQL_MultOperator @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.SQL_Expression right = new com.eagle.programmar.SQL.SQL_Expression(this, AllowedPrecedence.HIGHER);
		public SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

		public class SQL_MultOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_PunctuationChoice XXop = new com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice("*", "/", "%");
			public SQL_PunctuationChoice XXop = new SQL_PunctuationChoice("*", "/", "%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Keyword XXDIV = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DIV");
			public SQL_Keyword XXDIV = new SQL_Keyword("DIV");
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

			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.ToUpper())
			{
			case "*":
				interpreter.pushInt(leftInt * rightInt);
				return;
			case "DIV":
				interpreter.pushInt(leftInt / rightInt);
				return;
			case "/":
				interpreter.pushDouble((double) leftInt / rightInt);
				return;
			case "%":
				interpreter.pushInt(leftInt % rightInt);
				return;
			}
			throw new Exception("Unexpected multiplicative operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			string oper = @operator.getWhich().ToString();
			switch (oper.ToUpper())
			{
			case "*":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.TIMES, rightExpr, this);
			case "DIV":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
			case "/":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr, this);
			case "%":
				return generator.newMultiplicativeExpression(leftExpr, EagleGenerator.MultiplicativeEnum.REMAINDER, rightExpr, this);
			default:
				throw new Exception("Unexpected multiplicative operator: " + oper);
			}
		}
	}

}
