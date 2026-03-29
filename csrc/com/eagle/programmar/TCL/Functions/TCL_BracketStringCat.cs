// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 17, 2025

namespace com.eagle.programmar.TCL.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_BracketStringCat : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Terminals.TCL_Keyword STRING = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("string");
		public TCL_Keyword STRING = new TCL_Keyword("string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.Terminals.TCL_Keyword CAT = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("cat");
		public TCL_Keyword CAT = new TCL_Keyword("cat");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.TCL.TCL_Expression> strings;
		public TokenList<TCL_Expression> strings;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, CAT.getValue(), CAT);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			StringBuilder sb = new StringBuilder();
			foreach (TCL_Expression expr in strings._elements)
			{
				EagleValue val = interpreter.getEagleValue(expr);
				string str = val.forceStringValue();
				argTypes.Add(val.getType());
				sb.Append(str);
			}
			_metrics.calledWith(argTypes);
			interpreter.pushStr(sb.ToString());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Oper2Types types = null;

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(CAT);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			AbstractExpression result = null;
			int i = 0;
			foreach (TCL_Expression expr in strings._elements)
			{
				AbstractExpression piece = transformer.transformExpression(generator, expr);
				if (result == null)
				{
					result = piece;
				}
				else
				{
					if (metrics != null)
					{
						types._type1 = metrics[i - 1];
						types._type2 = metrics[i];
					}

					result = generator.newAppendExpression(types, result, piece, expr);
				}
				i++;
			}
			return result;
		}
	}

}
