// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vn9r/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword IF1 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("IF");
		public @DOC("6j4m0vn9r/index.html") Fortran_Keyword IF1 = new Fortran_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Fortran_Expression condition;
		public Fortran_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword THEN = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("THEN");
		public Fortran_Keyword THEN = new Fortran_Keyword("THEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln1;
		public Fortran_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Fortran.Fortran_Statement> thenStatements;
		public TokenList<Fortran_Statement> thenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Fortran_IfElseBlock elseClause;
		public @OPT Fortran_IfElseBlock elseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword END = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("END");
		public Fortran_Keyword END = new Fortran_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword IF2 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("IF");
		public Fortran_Keyword IF2 = new Fortran_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln2;
		public Fortran_EOLN eoln2;

		public static class Fortran_IfElseBlock extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword ELSE = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("ELSE");
			public Fortran_Keyword ELSE = new Fortran_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
			public Fortran_EOLN eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Fortran.Fortran_Statement> elseStatements;
			public TokenList<Fortran_Statement> elseStatements;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Fortran_Statement> todo;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF1));
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = thenStatements;
			}
			else
			{
				todo = null;

				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = elseClause.elseStatements;
				}
			}

			result = Eagle_Statement_Result.NORMAL;
			if (todo != null)
			{
				foreach (Fortran_Statement stmt in todo._elements)
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

			foreach (Fortran_Statement stmt1 in thenStatements._elements)
			{
				List<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
				if (stmts2 != null)
				{
					foreach (AbstractStatement stmt3 in stmts2)
					{
						ifTrue.Add(stmt3);
					}
				}
			}

			if (this.elseClause != null && this.elseClause.isPresent())
			{
				foreach (Fortran_Statement stmt4 in elseClause.elseStatements._elements)
				{
					List<AbstractStatement> stmts5 = transformer.transformStatement(generator, stmt4.getWhich());
					if (stmts5 != null)
					{
						foreach (AbstractStatement stmt6 in stmts5)
						{
							ifFalse.Add(stmt6);
						}
					}
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
