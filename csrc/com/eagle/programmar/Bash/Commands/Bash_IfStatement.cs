// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

namespace com.eagle.programmar.Bash.Commands
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Bash_Condition = com.eagle.programmar.Bash.Bash_Condition;
	using Bash_EndOfLine = com.eagle.programmar.Bash.Bash_EndOfLine;
	using Bash_Element = com.eagle.programmar.Bash.Bash_Element;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Conditional-Constructs") com.eagle.programmar.Bash.Terminals.Bash_Keyword IF = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("if");
		public @DOC("#Conditional-Constructs") Bash_Keyword IF = new Bash_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Condition condition;
		public Bash_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_EndOfLine eoln1;
		public Bash_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_Keyword THEN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("then");
		public Bash_Keyword THEN = new Bash_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Bash_EndOfLine eoln2;
		public @OPT Bash_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Element> statements;
		public TokenList<Bash_Element> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<Bash_If_Elif> elseIfBlock;
		public @OPT TokenList<Bash_If_Elif> elseIfBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Bash_If_Else elseBlock;
		public @OPT Bash_If_Else elseBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Bash.Terminals.Bash_Keyword FI = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("fi");
		public Bash_Keyword FI = new Bash_Keyword("fi");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Bash_If_Elif extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword ELIF = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("elif");
			public Bash_Keyword ELIF = new Bash_Keyword("elif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Condition condition;
			public Bash_Condition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_EndOfLine eoln1;
			public Bash_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_Keyword THEN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("then");
			public Bash_Keyword THEN = new Bash_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Bash_EndOfLine eoln2;
			public @OPT Bash_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Element> statements;
			public TokenList<Bash_Element> statements;
		}

		public static class Bash_If_Else extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword ELSE = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("else");
			public Bash_Keyword ELSE = new Bash_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Bash_EndOfLine eoln;
			public @OPT Bash_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_Element> statements;
			public TokenList<Bash_Element> statements;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Bash_Element> todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (elseIfBlock != null)
				{
					foreach (Bash_If_Elif elif in elseIfBlock._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
					}
				}

				if (elseBlock != null && elseBlock.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseBlock.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = statements;
			}
			else
			{
				int seq = 1;
				// Check for each 'else if'
				if (elseIfBlock != null)
				{
					foreach (Bash_If_Elif elif in elseIfBlock._elements)
					{
						bool cond2 = interpreter.getBoolValue(elif.condition);
						_metrics.get(seq).completedIf(cond2);
						seq++;
						if (cond2)
						{
							todo = elif.statements;
							break;
						}
					}
				}

				// Check for 'else'
				if (todo == null)
				{
					if (elseBlock != null && elseBlock.isPresent())
					{
						_metrics.get(seq).completedIf(true);
						todo = elseBlock.statements;
					}
				}
			}

			if (todo != null)
			{
				result = Eagle_Statement_Result.NORMAL;
				foreach (Bash_Element stmt in todo._elements)
				{
					result = interpreter.tryToInterpret(stmt.element);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}

			return result;
		}
	}

}
