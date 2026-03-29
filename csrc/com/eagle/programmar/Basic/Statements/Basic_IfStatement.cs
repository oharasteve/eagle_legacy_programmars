// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_StateMachine = com.eagle.programmar.Basic.Basic_StateMachine;
	using Basic_BaseStatement = com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using Basic_KeywordChoice = com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Basic_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword IF = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("IF");
		public Basic_Keyword IF = new Basic_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Basic_Expression condition;
		public Basic_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice THEN = new com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice("THEN", "THE");
		public Basic_KeywordChoice THEN = new Basic_KeywordChoice("THEN", "THE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Basic_IfWhat ifWhat;
		public Basic_IfWhat ifWhat;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public class Basic_IfWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Basic_Number XXlabel;
			public Basic_Number XXlabel;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_BaseStatement XXstatement;
			public Basic_BaseStatement XXstatement;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);

			if (cond1)
			{
				if (ifWhat.getWhich() is Basic_BaseStatement)
				{
					Basic_BaseStatement stmt = (Basic_BaseStatement) ifWhat.getWhich();
					result = interpreter.tryToInterpret(stmt);
				}
				else // Must be a Basic_Number
				{
					Basic_Number label = (Basic_Number) ifWhat.getWhich();
					int lbl = int.Parse(label.getValue());
					Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
					state.gotoStatement(lbl); // Goto this label
				}
			}

			return result;
		}
	}

}
