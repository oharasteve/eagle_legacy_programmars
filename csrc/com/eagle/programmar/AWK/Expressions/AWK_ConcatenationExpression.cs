// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2024

namespace com.eagle.programmar.AWK.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_ConcatenationExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) AWK_ConcatPiece piece1;
		public AWK_ConcatPiece piece1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) AWK_ConcatPiece piece2;
		public AWK_ConcatPiece piece2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<AWK_ConcatPiece> pieces;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public class AWK_ConcatPiece : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_String XXstring;
			public AWK_String XXstring;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_VariableExpression XXvariable;
			public AWK_VariableExpression XXvariable;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, "CONCAT", this);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			StringBuilder sb = new StringBuilder();

			EagleValue val1 = interpreter.getEagleValue(piece1.getWhich());
			string str1 = val1.forceStringValue();
			argTypes.Add(val1.getType());
			sb.Append(str1);

			EagleValue val2 = interpreter.getEagleValue(piece2.getWhich());
			string str2 = val2.forceStringValue();
			argTypes.Add(val2.getType());
			sb.Append(str2);

			if (pieces != null && pieces.isPresent())
			{
				foreach (AWK_ConcatPiece piece in pieces._elements)
				{
					EagleValue val = interpreter.getEagleValue(piece.getWhich());
					string str = val.forceStringValue();
					argTypes.Add(val.getType());
					sb.Append(str);
				}
			}
			interpreter.pushStr(sb.ToString());
			_metrics.calledWith(argTypes);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Oper2Types types = null;

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(this);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			AbstractExpression line = addPiece(generator, metrics, types, 0, piece1);
			AbstractExpression next = addPiece(generator, metrics, types, 1, piece2);
			line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece2);
			if (pieces != null && pieces.isPresent())
			{
				int i = 2;
				foreach (AWK_ConcatPiece piece in pieces._elements)
				{
					next = addPiece(generator, metrics, types, i, piece);
					line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece);
					i++;
				}
			}

			return line;
		}

		private static AbstractExpression addPiece(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, List<EagleGenerator.TypeEnum> metrics, Oper2Types types, int i, AWK_ConcatPiece piece)
		{
			if (metrics != null)
			{
				types._type1 = EagleGenerator.TypeEnum.STRING;
				types._type2 = metrics[i];
			}

			AbstractToken which1 = piece.getWhich();
			if (which1 is AWK_String)
			{
				AWK_String str = (AWK_String) which1;
				string lit = str.literal.getValue();
				if (lit.StartsWith("\"", StringComparison.Ordinal))
				{
					lit = lit.Substring(1, (lit.Length - 1) - 1);
				}
				return generator.newLiteralExpression(lit, piece);
			}
			else if (which1 is AWK_VariableExpression)
			{
				AWK_VariableExpression varExpr = (AWK_VariableExpression) which1;
				return generator.newVariableExpression(varExpr.variable.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, piece);
			}
			else
			{
				throw new Exception("Unable to handle: " + which1);
			}
		}
	}

}
