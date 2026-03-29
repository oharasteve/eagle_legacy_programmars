// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Statement = com.eagle.programmar.Eaglish.Eaglish_Statement;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_If_Block : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword IF = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("IF");
		public Eaglish_Keyword IF = new Eaglish_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Eaglish_Expression condition;
		public Eaglish_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
		public Eaglish_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Eaglish.Eaglish_Statement> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Eaglish_If_ElseIf_Block> elseifBlocks;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Eaglish_If_Else_Block elseBlock;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword END_IF = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("END_IF");
		public Eaglish_Keyword END_IF = new Eaglish_Keyword("END_IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln2;
		public Eaglish_EndOfLine eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public class Eaglish_If_ElseIf_Block : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword ELSE_IF = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("ELSE_IF");
			public Eaglish_Keyword ELSE_IF = new Eaglish_Keyword("ELSE_IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Eaglish_Expression condition;
			public Eaglish_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
			public Eaglish_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Eaglish.Eaglish_Statement> statements;
			public  OPT;
		}

		public class Eaglish_If_Else_Block : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword ELSE = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("ELSE");
			public Eaglish_Keyword ELSE = new Eaglish_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
			public Eaglish_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Eaglish.Eaglish_Statement> statements;
			public  OPT;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Eaglish_Statement> todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (elseifBlocks != null)
				{
					foreach (Eaglish_If_ElseIf_Block elif in elseifBlocks._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSE_IF));
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
				if (elseifBlocks != null)
				{
					foreach (Eaglish_If_ElseIf_Block elif in elseifBlocks._elements)
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
				foreach (Eaglish_Statement stmt in todo._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}

			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);

			List<AbstractStatement> thenParts = new List<AbstractStatement>();
			foreach (Eaglish_Statement thenStmt in statements._elements)
			{
				AbstractStatement thenPiece = transformer.transformStatement1(generator, thenStmt.getWhich());
				thenParts.Add(thenPiece);
			}

			List<AbstractStatement> elseParts = null;
			if (elseBlock != null && elseBlock.isPresent())
			{
				elseParts = new List<AbstractStatement>();
				foreach (Eaglish_Statement elseStmt in elseBlock.statements._elements)
				{
					AbstractStatement elsePiece = transformer.transformStatement1(generator, elseStmt.getWhich());
					elseParts.Add(elsePiece);
				}
			}

			return generator.newIfStatement(cond, thenParts, elseParts, this);
		}
	}

}
