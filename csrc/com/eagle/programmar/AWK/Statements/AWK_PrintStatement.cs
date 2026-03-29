// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using AWK_ArgumentList = com.eagle.programmar.AWK.AWK_ArgumentList;
	using AWK_MoreArguments = com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
	using AWK_KeywordChoice = com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_PrintStatement : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#print") com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice PRINT = new com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice("print", "printf");
		public @DOC("#print") AWK_KeywordChoice PRINT = new AWK_KeywordChoice("print", "printf");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) AWK_PrintParameters params;
		public AWK_PrintParameters @params;

		public static class AWK_PrintParameters extends TokenChooser implements AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST AWK_Print_WithParens XXwithParens;
			public AWK_Print_WithParens XXwithParens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Print_NoParens XXnoParens;
			public AWK_Print_NoParens XXnoParens;
		}

		public static class AWK_Print_WithParens extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT AWK_ArgumentList argList;
			public @OPT AWK_ArgumentList argList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class AWK_Print_NoParens extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT AWK_ArgumentList argList;
			public @OPT AWK_ArgumentList argList;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			AWK_ArgumentList args;
			if (@params.getWhich() is AWK_Print_WithParens)
			{
				args = ((AWK_Print_WithParens) @params.getWhich()).argList;
			}
			else if (@params.getWhich() is AWK_Print_NoParens)
			{
				args = ((AWK_Print_NoParens) @params.getWhich()).argList;
			}
			else
			{
				throw new Exception("Unexpected print argument: " + @params.ToString());
			}

			EagleValue val = interpreter.getEagleValue(args.expr);
			string result = val.forceStringValue();
			argTypes.Add(val.getType());
			Console.Write(result);

			if (args.more != null)
			{
				foreach (AWK_ArgumentList.AWK_MoreArguments nxt in args.more._elements)
				{
					val = interpreter.getEagleValue(nxt.expr);
					result = val.forceStringValue();
					argTypes.Add(val.getType());
					Console.Write(result);
				}
			}

			_metrics.calledWith(argTypes);
			Console.WriteLine();
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = null;
			Oper2Types types = null;
			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			AWK_ArgumentList argList;
			AbstractToken which1 = @params.getWhich();
			if (which1 is AWK_Print_WithParens)
			{
				AWK_Print_WithParens with = (AWK_Print_WithParens) which1;
				argList = with.argList;
			}
			else if (which1 is AWK_Print_NoParens)
			{
				AWK_Print_NoParens without = (AWK_Print_NoParens) which1;
				argList = without.argList;
			}
			else
			{
				throw new Exception("Unable to handle " + which1);
			}

			line = transformer.transformExpression(generator, argList.expr);
			int i = 0;
			foreach (AWK_ArgumentList.AWK_MoreArguments more in argList.more._elements)
			{
				i++;
				if (metrics != null && i < metrics.Count)
				{
					types._type1 = EagleGenerator.TypeEnum.STRING;
					types._type2 = metrics[i];
				}

				AbstractExpression next = transformer.transformExpression(generator, more.expr);
				line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, this);
			}
			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
		}
	}

}
