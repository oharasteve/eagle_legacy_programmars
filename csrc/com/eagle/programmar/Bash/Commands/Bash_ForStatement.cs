// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Bash_Element = com.eagle.programmar.Bash.Bash_Element;
	using Bash_EndOfLine = com.eagle.programmar.Bash.Bash_EndOfLine;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_RangeExpression = com.eagle.programmar.Bash.Expressions.Bash_RangeExpression;
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Looping-Constructs") com.eagle.programmar.Bash.Terminals.Bash_Keyword FOR = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("for");
		public @DOC("#Looping-Constructs") Bash_Keyword FOR = new Bash_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference id;
		public Bash_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Terminals.Bash_Keyword IN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("in");
		public Bash_Keyword IN = new Bash_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Expression> values;
		public TokenList<Bash_Expression> values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Bash.Bash_EndOfLine eoln1;
		public Bash_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Bash.Terminals.Bash_Keyword DO = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("do");
		public Bash_Keyword DO = new Bash_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Bash_EndOfLine eoln2;
		public @OPT Bash_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Element> statements;
		public TokenList<Bash_Element> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Bash.Terminals.Bash_Keyword DONE = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("done");
		public Bash_Keyword DONE = new Bash_Keyword("done");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (!(values._elements.get(0).getWhich() is Bash_RangeExpression))
			{
				throw new Exception("FOR statement requires a Range of values");
			}
			Bash_RangeExpression range = (Bash_RangeExpression) values._elements.get(0).getWhich();
			int start = interpreter.getIntValue(range.start);
			int stop = interpreter.getIntValue(range.stop);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			int i = start;
			while (true)
			{
				if (start < stop && i > stop)
				{
					break;
				}
				if (start > stop && i < stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(id, id.getValue(), new EagleInteger(i));

				foreach (Bash_Element stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt.element);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				if (start < stop)
				{
					i++;
				}
				if (start > stop)
				{
					i--;
				}
			}

			_metrics.competedLoop(metric, start > stop);
			return result;
		}
	}

}
