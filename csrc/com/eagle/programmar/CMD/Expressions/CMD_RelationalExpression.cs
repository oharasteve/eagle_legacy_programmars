// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

namespace com.eagle.programmar.CMD.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Format = com.eagle.programmar.CMD.CMD_Format;
	using CMD_KeywordChoice = com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CMD_RelationalExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.CMD_Expression left = new com.eagle.programmar.CMD.CMD_Expression(this, AllowedPrecedence.ATLEAST);
		public CMD_Expression left = new CMD_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice operator = new com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice("gtr", "leq", "lss", "geq");
		public CMD_KeywordChoice @operator = new CMD_KeywordChoice("gtr", "leq", "lss", "geq");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression right = new com.eagle.programmar.CMD.CMD_Expression(this, AllowedPrecedence.HIGHER);
		public CMD_Expression right = new CMD_Expression(this, AllowedPrecedence.HIGHER);

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

			string leftStr = leftValue.forceStringValue();
			string leftVal = CMD_Format.format(interpreter, leftStr);
			int leftInt = int.Parse(leftVal);
			string rightStr = rightValue.forceStringValue();
			string rightVal = CMD_Format.format(interpreter, rightStr);
			int rightInt = int.Parse(rightVal);
			switch (oper.ToLower())
			{
			case "lss":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "leq":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case "gtr":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case "geq":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			default:
				throw new Exception("Cannot handle relational operator: " + oper);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator);
			string oper = @operator.ToString();

			switch (oper.ToLower())
			{
			case "lss":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_THAN, rightExpr, this);
			case "leq":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, rightExpr, this);
			case "gtr":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_THAN, rightExpr, this);
			case "geq":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + oper);
			}
		}
	}

}
