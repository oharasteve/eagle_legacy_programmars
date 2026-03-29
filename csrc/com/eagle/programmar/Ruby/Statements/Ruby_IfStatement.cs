// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Statement = com.eagle.programmar.Ruby.Ruby_Statement;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_Keyword = com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ruby_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control_expressions_rdoc.html#label-if+Expression") com.eagle.programmar.Ruby.Terminals.Ruby_Keyword IF = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("if");
		public @DOC("control_expressions_rdoc.html#label-if+Expression") Ruby_Keyword IF = new Ruby_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Ruby_Expression condition;
		public Ruby_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln1;
		public Ruby_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Ruby.Ruby_Statement> thenStatements;
		public TokenList<Ruby_Statement> thenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Ruby_IfElseClause elseClause;
		public @OPT Ruby_IfElseClause elseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword END = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("end");
		public Ruby_Keyword END = new Ruby_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln2;
		public Ruby_EOLN eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Ruby_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword ELSE = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("else");
			public Ruby_Keyword ELSE = new Ruby_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Ruby_EOLN eoln2;
			public @OPT Ruby_EOLN eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Ruby.Ruby_Statement> elseStatements;
			public TokenList<Ruby_Statement> elseStatements;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Ruby_Statement> todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			bool cond = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond);
			if (cond)
			{
				todo = thenStatements;
			}
			else
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = elseClause.elseStatements;
				}
			}

			if (todo != null)
			{
				foreach (Ruby_Statement stmt in todo._elements)
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

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			foreach (Ruby_Statement stmt in thenStatements._elements)
			{
				List<AbstractStatement> stmts = transformer.transformStatement(generator, stmt.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement newStmt in stmts)
					{
						ifTrue.Add(newStmt);
					}
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				foreach (Ruby_Statement stmt in elseClause.elseStatements._elements)
				{
					foreach (AbstractStatement newStmt in transformer.transformStatement(generator, stmt.getWhich()))
					{
						ifFalse.Add(newStmt);
					}
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
