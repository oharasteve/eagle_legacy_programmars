// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2025

namespace com.eagle.programmar.SQL.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_ConcatFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CONCAT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CONCAT");
		public SQL_Keyword CONCAT = new SQL_Keyword("CONCAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.SQL_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public SeparatedList<SQL_Expression, PunctuationComma> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, CONCAT.getValue(), CONCAT);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			StringBuilder result = new StringBuilder();
			for (int i = 0; i < exprs.getPrimaryCount(); i++)
			{
				SQL_Expression expr = exprs.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				string piece = val.forceStringValue();
				argTypes.Add(val.getType());
				result.Append(piece.replaceAll("\\\\n", "\n")); // Replace \\n with a newline)
			}
			interpreter.pushStr(result.ToString());
			_metrics.calledWith(argTypes);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = null;
			Oper2Types types = null;

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(CONCAT);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			int numPieces = exprs.getPrimaryCount();
			for (int i = 0; i < numPieces; i++)
			{
				if (metrics != null)
				{
					types._type1 = EagleGenerator.TypeEnum.STRING;
					types._type2 = metrics[i];
				}

				SQL_Expression piece = exprs.getPrimaryElement(i);
				AbstractExpression next = transformer.transformExpression(generator, piece);
				if (i == 0)
				{
					line = next;
				}
				else
				{
					line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece);
				}
			}

			return line;
		}
	}

}
