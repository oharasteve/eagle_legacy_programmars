// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Writeln_Statement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("System.Writeln") com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice WRITELN = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Write", "WriteLn");
		public @DOC("System.Writeln") Delphi_KeywordChoice WRITELN = new Delphi_KeywordChoice("Write", "WriteLn");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_WriteLn_Something something;
		public @OPT Delphi_WriteLn_Something something;

		public static class Delphi_WriteLn_Piece extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Expression expr;
			public Delphi_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_Writeln_ColonWidth width;
			public @OPT Delphi_Writeln_ColonWidth width;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Writeln_ColonWidth precision;
			public @OPT Delphi_Writeln_ColonWidth precision;

			public static class Delphi_Writeln_ColonWidth extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Expression width;
				public Delphi_Expression width;
			}
		}

		public static class Delphi_WriteLn_Something extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Delphi_WriteLn_Piece, com.eagle.tokens.punctuation.PunctuationComma> pieces;
			public @OPT SeparatedList<Delphi_WriteLn_Piece, PunctuationComma> pieces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, WRITELN.getValue(), WRITELN);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			for (int i = 0; i < something.pieces.getPrimaryCount(); i++)
			{
				Delphi_WriteLn_Piece piece = something.pieces.getPrimaryElement(i);
				if (piece.width != null && piece.width.isPresent())
				{
					throw new Exception("Can't handle field widths");
				}
				EagleValue val = interpreter.getEagleValue(piece.expr);
				string result = val.forceStringValue();
				argTypes.Add(val.getType());
				Console.Write(result);
			}
			_metrics.calledWith(argTypes);
			Console.WriteLine();
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = null;
			Oper2Types types = null;
			if (something != null && something.isPresent())
			{
				// Pick up metrics, if known
				List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(WRITELN);
				if (metrics != null)
				{
					types = new Oper2Types();
				}

				int numPieces = something.pieces.getPrimaryCount();
				for (int i = 0; i < numPieces; i++)
				{
					Delphi_WriteLn_Piece piece = something.pieces.getPrimaryElement(i);
					if (piece.width != null && piece.width.isPresent())
					{
						throw new Exception("Can't handle field widths");
					}
					if (i == 0)
					{
						line = transformer.transformExpression(generator, piece.expr);
					}
					else
					{
						if (metrics != null)
						{
							types._type1 = metrics[i - 1];
							types._type2 = metrics[i];
						}

						AbstractExpression next = transformer.transformExpression(generator, piece.expr);
						line = generator.newAdditiveExpression(types, line, EagleGenerator.AdditiveEnum.PLUS, next, piece);
					}
				}
			}

			bool newLine;
			switch (WRITELN.getValue().ToLower())
			{
			case "write":
				newLine = false;
				break;
			case "writeln":
				newLine = true;
				break;
			default:
				throw new Exception("Unexpected write command: " + WRITELN.getValue());
			}

			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, newLine, false, this);
		}
	}

}
