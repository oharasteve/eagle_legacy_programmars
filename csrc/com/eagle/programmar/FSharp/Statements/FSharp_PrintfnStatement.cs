// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Format = com.eagle.programmar.FSharp.FSharp_Format;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_PrintfnStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("plaintext-formatting") com.eagle.programmar.FSharp.Terminals.FSharp_Keyword PRINTFN = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("printfn");
		public @DOC("plaintext-formatting") FSharp_Keyword PRINTFN = new FSharp_Keyword("printfn");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.FSharp.FSharp_Expression> arguments;
		public TokenList<FSharp_Expression> arguments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln;
		public FSharp_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTFN.getValue(), PRINTFN);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			string formatted = FSharp_Format.format(interpreter, arguments, argTypes);
			_metrics.calledWith(argTypes);
			Console.WriteLine(formatted);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(PRINTFN);
			AbstractExpression fullExpr = FSharp_Format.transform(transformer, generator, arguments, metrics);
			return generator.newPrintStatement(fullExpr, EagleGenerator.TypeEnum.STRING, true, false, this);
		}
	}

}
