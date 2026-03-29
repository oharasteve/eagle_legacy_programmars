// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

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
	using Ada_Punctuation = com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_PutIntegerStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword INTEGER_IO = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("Integer_IO");
		public Ada_Keyword INTEGER_IO = new Ada_Keyword("Integer_IO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice PUT = new com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice("put");
		public Ada_KeywordChoice PUT = new Ada_KeywordChoice("put");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ada.Ada_Expression expr;
		public Ada_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ada.Terminals.Ada_Keyword WIDTH = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("Width");
		public Ada_Keyword WIDTH = new Ada_Keyword("Width");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Ada.Terminals.Ada_Punctuation arrow = new com.eagle.programmar.Ada.Terminals.Ada_Punctuation("=>");
		public Ada_Punctuation arrow = new Ada_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Ada.Ada_Expression width;
		public Ada_Expression width;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator1Metrics _metrics = null;
		private Operator1Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new Operator1Metrics(interpreter._metrics, PUT, PUT.getValue());
			}

			EagleValue result = interpreter.getEagleValue(expr);
			EagleGenerator.TypeEnum argType = result.getType();
			string val = result.forceStringValue();
			_metrics.operated(argType);
			Console.Write(val);
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
