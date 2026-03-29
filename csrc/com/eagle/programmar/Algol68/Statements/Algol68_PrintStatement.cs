// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Keyword = com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
	using Algol68_Punctuation = com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_PrintStatement : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword PRINT = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("PRINT");
		public Algol68_Keyword PRINT = new Algol68_Keyword("PRINT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation leftParen1 = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("(");
		public Algol68_Punctuation leftParen1 = new Algol68_Punctuation("(");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation leftParen2 = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("(");
		public Algol68_Punctuation leftParen2 = new Algol68_Punctuation("(");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<Algol68_PrintWhat, com.eagle.tokens.punctuation.PunctuationComma> pieces;
		public SeparatedList<Algol68_PrintWhat, PunctuationComma> pieces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation rightParen1 = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation(")");
		public Algol68_Punctuation rightParen1 = new Algol68_Punctuation(")");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation rightParen2 = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation(")");
		public Algol68_Punctuation rightParen2 = new Algol68_Punctuation(")");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationSemicolon semicolon;
		public  OPT;

		public class Algol68_PrintNewLine : TokenSequence, AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword NEW = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("NEW");
			public Algol68_Keyword NEW = new Algol68_Keyword("NEW");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword LINE = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("LINE");
			public Algol68_Keyword LINE = new Algol68_Keyword("LINE");
		}

		public class Algol68_PrintWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Expression XXexpr;
			public Algol68_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_PrintNewLine XXnewLine;
			public Algol68_PrintNewLine XXnewLine;
		}

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

			for (int i = 0; i < pieces.getPrimaryCount(); i++)
			{
				AbstractToken piece = pieces.getPrimaryElement(i).getWhich();
				if (piece is Algol68_Expression)
				{
					EagleValue val = interpreter.getEagleValue(piece);
					string result = val.forceStringValue();
					argTypes.Add(val.getType());
					Console.Write(result);
				}
				else if (piece is Algol68_PrintNewLine)
				{
					Console.WriteLine();
				}
				else
				{
					throw new Exception("Unable to print " + pieces);
				}
			}

			_metrics.calledWith(argTypes);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = null;
			Oper2Types types = null;
			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);
			if (metrics != null)
			{
				types = new Oper2Types();
			}

			int numPieces = pieces.getPrimaryCount();
			bool newLine = false;
			for (int i = 0; i < numPieces; i++)
			{
				Algol68_PrintWhat piece = pieces.getPrimaryElement(i);
				AbstractToken whichPiece = piece.getWhich();
				if (whichPiece is Algol68_PrintNewLine)
				{
					newLine = true;
				}
				else if (whichPiece is Algol68_Expression)
				{
					Algol68_Expression expr = (Algol68_Expression) whichPiece;
					if (line == null)
					{
						line = transformer.transformExpression(generator, expr);
					}
					else
					{
						if (metrics != null && i < metrics.Count)
						{
							types._type1 = metrics[i - 1];
							types._type2 = metrics[i];
						}

						AbstractExpression next = transformer.transformExpression(generator, expr);
						line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece);
					}
				}
				else
				{
					throw new Exception("Unable to handle: " + whichPiece);
				}
			}
			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, newLine, false, this);
		}
	}

}
