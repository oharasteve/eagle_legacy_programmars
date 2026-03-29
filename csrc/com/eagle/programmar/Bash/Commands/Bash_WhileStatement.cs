// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Bash_Condition = com.eagle.programmar.Bash.Bash_Condition;
	using Bash_EndOfLine = com.eagle.programmar.Bash.Bash_EndOfLine;
	using Bash_Element = com.eagle.programmar.Bash.Bash_Element;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_WhileStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Looping-Constructs") com.eagle.programmar.Bash.Terminals.Bash_Keyword WHILE = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("while");
		public @DOC("#Looping-Constructs") Bash_Keyword WHILE = new Bash_Keyword("while");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Condition condition;
		public Bash_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_EndOfLine eoln1;
		public Bash_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_Keyword DO = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("do");
		public Bash_Keyword DO = new Bash_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Bash_EndOfLine eoln2;
		public @OPT Bash_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Element> statements;
		public TokenList<Bash_Element> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Bash.Terminals.Bash_Keyword DONE = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("done");
		public Bash_Keyword DONE = new Bash_Keyword("done");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, WHILE);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(condition);
				if (!keepGoing)
				{
					break;
				}

				metric.iterate();
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
			}

			_metrics.competedLoop(metric, false);
			return result;
		}
	}

}
