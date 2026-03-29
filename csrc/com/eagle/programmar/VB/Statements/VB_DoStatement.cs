// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2024

namespace com.eagle.programmar.VB.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_DoStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/do-loop-statement") com.eagle.programmar.VB.Terminals.VB_Keyword DO = new com.eagle.programmar.VB.Terminals.VB_Keyword("Do");
		public @DOC("statements/do-loop-statement") VB_Keyword DO = new VB_Keyword("Do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT VB_DoCondition cond1;
		public @OPT VB_DoCondition cond1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> actions;
		public TokenList<VB_Element> actions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_Keyword LOOP = new com.eagle.programmar.VB.Terminals.VB_Keyword("Loop");
		public VB_Keyword LOOP = new VB_Keyword("Loop");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT VB_DoCondition cond2;
		public @OPT VB_DoCondition cond2;

		public static class VB_DoCondition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_KeywordChoice WHILE = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("While", "Until");
			public VB_KeywordChoice WHILE = new VB_KeywordChoice("While", "Until");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression condition;
			public VB_Expression condition;
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

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				if (cond1 != null && cond1.isPresent())
				{
					bool cond = interpreter.getBoolValue(cond1.condition);
					if (cond1.WHILE.ToString().ToLower().Equals("while"))
					{
						cond = !cond;
					}
					if (cond)
					{
						break;
					}
				}

				metric.iterate();

				foreach (VB_Element stmt in actions._elements)
				{
					result = interpreter.tryToInterpret(stmt);
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

				if (cond2 != null && cond2.isPresent())
				{
					bool cond = interpreter.getBoolValue(cond2.condition);
					if (cond2.WHILE.ToString().ToLower().Equals("while"))
					{
						cond = !cond;
					}
					if (cond)
					{
						break;
					}
				}
			}

			_metrics.competedLoop(metric, false);
			return result;
		}
	}

}
