// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
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

	public class Julia_StringFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Terminals.Julia_Keyword STRING = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("string");
		public Julia_Keyword STRING = new Julia_Keyword("string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Julia.Julia_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public SeparatedList<Julia_Expression, PunctuationComma> argList;
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
				_metrics = new ArgumentsMetrics(interpreter._metrics, STRING.getValue(), STRING);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			StringBuilder buff = new StringBuilder();
			for (int i = 0; i < argList.getPrimaryCount(); i++)
			{
				Julia_Expression expr = argList.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				string piece = val.forceStringValue();
				argTypes.Add(val.getType());
				buff.Append(piece);
			}
			interpreter.pushStr(buff.ToString());
			_metrics.calledWith(argTypes);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = null;
			Oper2Types types = null;

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(STRING);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			int numPieces = argList.getPrimaryCount();
			for (int i = 0; i < numPieces; i++)
			{
				if (metrics != null)
				{
					types._type1 = EagleGenerator.TypeEnum.STRING;
					types._type2 = metrics[i];
				}

				Julia_Expression piece = argList.getPrimaryElement(i);
				if (i == 0)
				{
					line = transformer.transformExpression(generator, piece);
				}
				else
				{
					AbstractExpression next = transformer.transformExpression(generator, piece);
					line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece);
				}
			}

			return line;
		}
	}

}
