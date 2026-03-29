// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Statement = com.eagle.programmar.Rust.Rust_Statement;
	using Rust_ParenthesizedExpression = com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_IfStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("expressions/if-expr.html") @NEWLINE Rust_Keyword IF = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("if");
		public @DOC("expressions/if-expr.html") Rust_Keyword IF = new Rust_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression condition;
		public Rust_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Statement thenStatement;
		public Rust_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NEWLINE Rust_IfElseClause elseClause;
		public @OPT Rust_IfElseClause elseClause;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Rust_IfElseClause extends TokenSequence implements AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Keyword ELSE = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("else");
			public Rust_Keyword ELSE = new Rust_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Statement elseStatement;
			public Rust_Statement elseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Rust_Statement todo = null;

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

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = thenStatement;
			}
			else if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatement;
			}

			if (todo != null)
			{
				result = interpreter.tryToInterpret(todo);
			}

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			List<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
			if (stmts != null)
			{
				foreach (AbstractStatement stmt in stmts)
				{
					ifTrue.Add(stmt);
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				foreach (AbstractStatement stmt in transformer.transformStatement(generator, elseClause.elseStatement.getWhich()))
				{
					ifFalse.Add(stmt);
				}
			}

			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		public static Rust_Statement generateIfElseOne(Rust_Expression cond, Rust_Statement thenStmt, Rust_Statement elseStmt, AbstractToken source)
		{
			Rust_IfStatement ifStmt = new Rust_IfStatement();
			AbstractToken which = cond.getWhich();
			if (which is Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression parensExpr = (Rust_ParenthesizedExpression) which;
				// Remove redundant parens
				ifStmt.condition = parensExpr.expressions.first();
			}
			else
			{
				ifStmt.condition = cond;
			}

			ifStmt.thenStatement = thenStmt;

			if (elseStmt != null)
			{
				ifStmt.elseClause = new Rust_IfElseClause();
				ifStmt.elseClause.setPresent(true);
				ifStmt.elseClause.elseStatement = elseStmt;
				ifStmt.elseClause.elseStatement.setPresent(true);
			}

			ifStmt.setTransformationSource(source);
			return Rust_Generator.wrapStatement(ifStmt);
		}

		public static Rust_Statement generateIfElseMany(Rust_Expression cond, List<Rust_Statement> thenStatements, List<Rust_Statement> elseStatements, AbstractToken source)
		{
			Rust_Statement blockTrue = Rust_Block_Statement.generateBlock(thenStatements, source);

			Rust_Statement blockElse = null;
			if (elseStatements != null && elseStatements.size() > 0)
			{
				blockElse = Rust_Block_Statement.generateBlock(elseStatements, source);
			}

			return generateIfElseOne(cond, blockTrue, blockElse, source);
		}
	}

}
