// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
	using Ada_KeywordChoice = com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_PutStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Ada_Put_Unbounded_IO io;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice PUT = new com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice("put", "put_line");
		public Ada_KeywordChoice PUT = new Ada_KeywordChoice("put", "put_line");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Ada_Expression expr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class Ada_Put_Unbounded_IO : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword UNBOUNDED_IO = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("Unbounded_IO");
			public Ada_Keyword UNBOUNDED_IO = new Ada_Keyword("Unbounded_IO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator1Metrics _metrics = null;
		private Operator1Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new Operator1Metrics(interpreter._metrics, PUT, PUT.getValue());
			}

			string val = null;
			if (expr != null && expr.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(expr);
				EagleGenerator.TypeEnum argType = value.getType();
				val = value.forceStringValue();
				_metrics.operated(argType);
			}

			switch (PUT.getValue().ToLower())
			{
			case "put":
				if (!string.ReferenceEquals(val, null))
				{
					Console.Write(val);
				}
				return;
			case "put_line":
				if (!string.ReferenceEquals(val, null))
				{
					Console.WriteLine(val);
				}
				else
				{
					Console.WriteLine();
				}
				return;
			}
			throw new Exception("Unexpected PUT command: " + PUT.getValue());
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Operator1Metrics.Oper1Types metric = transformer.findOperator1Metric(PUT);
			EagleGenerator.TypeEnum type = null;
			if (metric != null)
			{
				type = metric._type1;
			}
			AbstractExpression fullExpr = transformer.transformExpression(generator, expr);
			bool newLine = PUT.getValue().ToLower().Equals("put_line");
			return generator.newPrintStatement(fullExpr, type, newLine, false, this);
		}
	}

}
