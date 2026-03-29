// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

namespace com.eagle.programmar.Lisp.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_Variable_Definition = com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_DoFunction : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
		public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("s_do.htm") com.eagle.programmar.Lisp.Terminals.Lisp_Keyword DO = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("do");
		public @DOC("s_do.htm") Lisp_Keyword DO = new Lisp_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
		public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Lisp_DoVariables variables;
		public @OPT Lisp_DoVariables variables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
		public PunctuationRightParen rightParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen3;
		public PunctuationLeftParen leftParen3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Lisp.Lisp_Expression terminateCondition;
		public Lisp_Expression terminateCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen3;
		public PunctuationRightParen rightParen3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<com.eagle.programmar.Lisp.Lisp_Expression> actions;
		public TokenList<Lisp_Expression> actions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
		public PunctuationRightParen rightParen1;

		public static class Lisp_DoVariables extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition var;
			public Lisp_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Lisp.Lisp_Expression initialValue;
			public Lisp_Expression initialValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Lisp_Expression increment;
			public @OPT Lisp_Expression increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, DO);
			}
			ForLoopMetric metric = new ForLoopMetric();

			EagleValue val = null;
			if (variables != null && variables.isPresent())
			{
				val = interpreter.getEagleValue(variables.initialValue);
				interpreter.setSymbol(variables.var, variables.var.getValue(), val);
			}

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool done = interpreter.getBoolValue(terminateCondition);
				if (done)
				{
					break;
				}

				metric.iterate();

				foreach (Lisp_Expression action in actions._elements)
				{
					result = interpreter.tryToInterpret(action);

					if (result == Eagle_Statement_Result.RETURN)
					{
						metric.broke();
					}

					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}

				if (variables.initialValue != null && variables.increment != null)
				{
					val = interpreter.getEagleValue(variables.increment);
					interpreter.setSymbol(variables.increment, variables.var.getValue(), val);
				}
			}

			bool backwards = false; // TODO -- this might be true in some cases
			_metrics.competedLoop(metric, backwards);

			if (variables != null && variables.var != null)
			{
				interpreter.removeSymbol(variables.var.getValue());
			}

			return Eagle_Statement_Result.NORMAL;
		}
	}

}
