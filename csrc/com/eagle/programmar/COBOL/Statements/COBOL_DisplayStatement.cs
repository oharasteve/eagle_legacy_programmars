// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

namespace com.eagle.programmar.COBOL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_DisplayWithNoAdvancing = com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions.COBOL_DisplayWithNoAdvancing;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
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
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_DisplayStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsdisp.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DISPLAY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DISPLAY");
		public @DOC("rlpsdisp.htm") COBOL_Keyword DISPLAY = new COBOL_Keyword("DISPLAY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_DisplayPosition position;
		public @OPT COBOL_DisplayPosition position;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<COBOL_DisplayClause> clauses;
		public TokenList<COBOL_DisplayClause> clauses;

		public static class COBOL_DisplayPosition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Expression x;
			public @OPT COBOL_Expression x;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Expression y;
			public COBOL_Expression y;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class COBOL_DisplayClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) COBOL_DisplayWhat what;
			public COBOL_DisplayWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_DisplayOptions> options;
			public @OPT TokenList<COBOL_DisplayOptions> options;
		}

		public static class COBOL_DisplayLine extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LINE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINE");
			public COBOL_Keyword LINE = new COBOL_Keyword("LINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression line;
			public COBOL_Expression line;
		}

		public static class COBOL_DisplayColumn extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COLUMN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COLUMN");
			public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression column;
			public COBOL_Expression column;
		}

		public static class COBOL_DisplayWhat extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.COBOL.COBOL_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
			public SeparatedList<COBOL_Expression, PunctuationComma> exprs;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, DISPLAY.getValue(), DISPLAY);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			foreach (COBOL_DisplayClause clause in clauses._elements)
			{
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < clause.what.exprs.getPrimaryCount(); i++)
				{
					COBOL_Expression expr = clause.what.exprs.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(expr);
					string result = val.forceStringValue();
					argTypes.Add(val.getType());
					sb.Append(result);
				}
				_metrics.calledWith(argTypes);
				Console.WriteLine(sb.ToString());
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (clauses.size() == 1)
			{
				COBOL_DisplayClause clause = clauses.first();
				AbstractExpression line = null;
				Oper2Types types = null;

				// Pick up metrics, if known
				List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(DISPLAY);
				if (metrics != null)
				{
					types = new Oper2Types();
				}

				bool newline = true;
				foreach (COBOL_DisplayOptions opt in clause.options._elements)
				{
					if (opt.getWhich() is COBOL_DisplayWithNoAdvancing)
					{
						newline = false;
					}
				}

				int numPieces = clause.what.exprs.getPrimaryCount();
				for (int i = 0; i < numPieces; i++)
				{
					COBOL_Expression expr = clause.what.exprs.getPrimaryElement(i);
					if (i == 0)
					{
						line = transformer.transformExpression(generator, expr);
					}
					else
					{
						if (metrics != null)
						{
							types._type1 = metrics[i - 1];
							types._type2 = metrics[i];
						}

						AbstractExpression next = transformer.transformExpression(generator, expr);
						line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, expr);
					}
				}

				return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, newline, false, this);
			}
			throw new Exception("Unable to handle DISPLAY: " + this);
		}
	}

}
