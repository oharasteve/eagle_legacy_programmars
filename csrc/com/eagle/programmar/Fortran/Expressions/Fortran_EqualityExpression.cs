// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Fortran.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator2Metrics = com.eagle.metrics.Operator2Metrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_KeywordChoice = com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
	using Fortran_PunctuationChoice = com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
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

	public class Fortran_EqualityExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Fortran_Expression left = new com.eagle.programmar.Fortran.Fortran_Expression(this, AllowedPrecedence.ATLEAST);
		public Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Fortran_EqOper operator;
		public Fortran_EqOper @operator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Fortran_Expression right = new com.eagle.programmar.Fortran.Fortran_Expression(this, AllowedPrecedence.HIGHER);
		public Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);

		public class Fortran_EqOper : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_KeywordChoice XXEQ = new com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice(".EQ.", ".NE.");
			public Fortran_KeywordChoice XXEQ = new Fortran_KeywordChoice(".EQ.", ".NE.");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_PunctuationChoice XXoper = new com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice("==", "/=");
			public Fortran_PunctuationChoice XXoper = new Fortran_PunctuationChoice("==", "/=");
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
				switch (oper.ToUpper())
				{
				case ".EQ.", "==":
					interpreter.pushBool(leftStr.Equals(rightStr));
					return;
				case ".NE.", "/=":
					interpreter.pushBool(!leftStr.Equals(rightStr));
					return;
				}
			}
			else
			{
				int leftInt = leftValue.forceIntegerValue();
				int rightInt = rightValue.forceIntegerValue();
				switch (oper.ToUpper())
				{
				case ".EQ.", "==":
					interpreter.pushBool(leftInt == rightInt);
					return;
				case ".NE.", "/=":
					interpreter.pushBool(leftInt != rightInt);
					return;
				}
			}
			throw new Exception("Unexpected equality operator: " + oper);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			// Fortran_EqOper is 41/25 (it's a space) and Fortran_PunctuationChoice is at 41/26 which is right
			Operator2Metrics.Oper2Types types = transformer.findOperator2Metric(@operator.getWhich());
			string oper = @operator.getWhich().ToString();

			switch (oper.ToUpper())
			{
			case ".EQ.", "==":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
			case ".NE.", "/=":
				return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.NOT_EQUALS, rightExpr, this);
			default:
				throw new Exception("Unexpected relational operator: " + oper);
			}
		}
	}

}
