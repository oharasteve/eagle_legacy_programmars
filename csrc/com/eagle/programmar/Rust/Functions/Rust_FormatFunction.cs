// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

namespace com.eagle.programmar.Rust.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Format = com.eagle.programmar.Rust.Rust_Format;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
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
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_FormatFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Keyword FORMAT = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("format");
		public Rust_Keyword FORMAT = new Rust_Keyword("format");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Punctuation bang = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("!");
		public Rust_Punctuation bang = new Rust_Punctuation("!");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.Rust.Rust_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public SeparatedList<Rust_Expression, PunctuationComma> argList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, FORMAT.getValue(), FORMAT);
			}

			string result = Rust_Format.format(interpreter, argList, _metrics);
			interpreter.pushStr(result);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(FORMAT);
			return Rust_Format.compile(transformer, generator, argList, metrics);
		}

		public static Rust_Expression generateFormat(Rust_Expression fmt, List<Rust_Expression> args, AbstractToken source)
		{
			Rust_FormatFunction func = new Rust_FormatFunction();
			func.leftParen = new PunctuationLeftParen();
			func.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
			func.argList.addPrimaryElement(fmt);
			func.rightParen = new PunctuationRightParen();

			foreach (Rust_Expression arg in args)
			{
				func.argList.addSecondaryElement(new PunctuationComma());
				func.argList.addPrimaryElement(arg);
			}

			func.setTransformationSource(source);
			return Rust_Generator.wrapExpression(func);
		}
	}

}
