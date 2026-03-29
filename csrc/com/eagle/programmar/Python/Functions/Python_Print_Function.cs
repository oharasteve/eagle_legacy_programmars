// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Assignment_Expression = com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_Literal = com.eagle.programmar.Python.Terminals.Python_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
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
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Print_Function : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword PRINT = new com.eagle.programmar.Python.Terminals.Python_Keyword("print");
		public Python_Keyword PRINT = new Python_Keyword("print");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE SeparatedList<com.eagle.programmar.Python.Python_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			int numExpr = exprs.getPrimaryCount();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < numExpr; i++)
			{
				if (i > 0)
				{
					sb.Append(' ');
				}
				EagleValue piece = interpreter.getEagleValue(exprs.getPrimaryElement(i));
				argTypes.Add(piece.getType());
				sb.Append(piece.forceStringValue());
			}

			_metrics.calledWith(argTypes);
			Console.WriteLine(sb.ToString());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);
			Oper2Types types = new Oper2Types();
			types._type1 = EagleGenerator.TypeEnum.STRING;

			int numExpr = exprs.getPrimaryCount();
			AbstractExpression space = null;
			if (numExpr > 1)
			{
				space = generator.newLiteralExpression(" ", null);
			}
			AbstractExpression result = null;
			for (int i = 0; i < numExpr; i++)
			{
				AbstractExpression piece = transformer.transformExpression(generator, exprs.getPrimaryElement(i));

				if (i == 0)
				{
					result = piece;
				}
				else
				{
					types._type2 = EagleGenerator.TypeEnum.STRING;
					result = generator.newAppendExpression(types, result, space, PRINT);
					types._type2 = metrics[i];
					result = generator.newAppendExpression(types, result, piece, PRINT);
				}
			}

			return generator.newPrintFunction(result, EagleGenerator.TypeEnum.STRING, true, false, this);
		}

		public static Python_Expression generatePrintFunc(Python_Expression line, EagleGenerator.TypeEnum type, bool newLine, AbstractToken source)
		{
			Python_Print_Function prtFunc = new Python_Print_Function();
			prtFunc.leftParen = new PunctuationLeftParen();
			prtFunc.exprs = new SeparatedList<Python_Expression, PunctuationComma>();
			prtFunc.exprs.addPrimaryElement(line);

			if (!newLine)
			{
				prtFunc.exprs.addSecondaryElement(new PunctuationComma());
				Python_Expression emptyExpr1 = Python_Literal.generateLiteralExpression("", null);
				Python_Variable end = Python_Variable.newVariable("end");
				Python_Expression asgExpr1 = Python_Assignment_Expression.generateAssignment(end, null, EagleGenerator.AssignmentEnum.EQUALS, emptyExpr1, source);
				prtFunc.exprs.addPrimaryElement(asgExpr1);
			}

			prtFunc.rightParen = new PunctuationRightParen();
			prtFunc.setTransformationSource(source);
			return Python_Generator.wrapExpression(prtFunc);
		}
	}

}
