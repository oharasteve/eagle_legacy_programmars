// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_List = com.eagle.programmar.Python.Python_List;
	using Python_BuiltIn = com.eagle.programmar.Python.Expressions.Python_BuiltIn;
	using Python_Literals = com.eagle.programmar.Python.Expressions.Python_Literals;
	using Python_Logical_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
	using Python_Logical_Or_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
	using Python_Or_Operation = com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression.Python_Or_Operation;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_Relational_Expression = com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
	using Python_IN_Operator = com.eagle.programmar.Python.Expressions.Python_Relational_Expression.Python_IN_Operator;
	using Python_Relational_Operator = com.eagle.programmar.Python.Expressions.Python_Relational_Expression.Python_Relational_Operator;
	using Python_Locals_Function = com.eagle.programmar.Python.Functions.Python_Locals_Function;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_ElseStartOfLine = com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_KeywordChoice = com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_WhileStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("compound_stmts.html#the-while-statement") @NOSPACE Python_Keyword WHILE = new com.eagle.programmar.Python.Terminals.Python_Keyword("while");
		public @DOC("compound_stmts.html#the-while-statement") Python_Keyword WHILE = new Python_Keyword("while");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression condition;
		public Python_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
		public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @PYDENT Python_StatementBlock statements;
		public @PYDENT Python_StatementBlock statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Python_WhileElse whileElse;
		public @OPT Python_WhileElse whileElse;

		public static class Python_WhileElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword ELSE = new com.eagle.programmar.Python.Terminals.Python_Keyword("else");
			public Python_Keyword ELSE = new Python_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Python_StatementBlock doWhat;
			public Python_StatementBlock doWhat;
		}

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
				if (!interpreter.getBoolValue(condition))
				{
					break;
				}

				metric.iterate();

				result = interpreter.tryToInterpret(statements);
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

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> actions = new List<AbstractStatement>();
			foreach (AbstractStatement stmt in statements.transformStatement(transformer, generator))
			{
				actions.Add(stmt);
			}
			return generator.newWhileStatement(cond, actions, this);
		}

		public static Python_ComplexStatement generateDoUntilOne(Python_Expression cond, Python_ComplexStatement action, AbstractToken source)
		{
			List<Python_ComplexStatement> actions = new List<Python_ComplexStatement>();
			actions.Add(action);
			return generateDoUntilMany(cond, actions, source);
		}

		public static Python_ComplexStatement generateDoUntilMany(Python_Expression cond, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			string oddName = "_not_first_time_at_line_" + source.getStartLine() + "_";

			Python_Parenthesized_Expression parensExpr = new Python_Parenthesized_Expression();
			parensExpr.leftParen = new PunctuationLeftParen();
			parensExpr.list = new Python_List();
			parensExpr.list.expr = cond;
			parensExpr.rightParen = new PunctuationRightParen();

			Python_Logical_Not_Expression notExpr = new Python_Logical_Not_Expression();
			notExpr.expr = Python_Generator.wrapExpression(parensExpr);
			notExpr.NOT = new Python_Keyword("not");

			Python_Literals lits = Python_Literals.generateLiterals(oddName, null);
			Python_Locals_Function localsFn = new Python_Locals_Function();
			localsFn.LOCALS = new Python_KeywordChoice("locals");
			Python_Relational_Expression.Python_IN_Operator inOper = new Python_Relational_Expression.Python_IN_Operator();
			inOper.NOT = new Python_Keyword("not");
			inOper.NOT.setPresent(true);
			inOper.IN = new Python_Keyword("in");

			Python_Relational_Expression inExpr = new Python_Relational_Expression();
			inExpr.left = Python_Generator.wrapExpression(lits);
			inExpr.right = Python_Generator.wrapExpression(localsFn);
			inExpr.@operator = new Python_Relational_Expression.Python_Relational_Operator();
			inExpr.@operator.setWhich(inOper);

			Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
			orExpr.left = Python_Generator.wrapExpression(inExpr);
			Python_Keyword OR = new Python_Keyword("or");
			orExpr.@operator = new Python_Logical_Or_Expression.Python_Or_Operation();
			orExpr.@operator.setWhich(OR);
			orExpr.right = Python_Generator.wrapExpression(notExpr);

			Python_Expression whileCond = Python_Generator.wrapExpression(orExpr);

			Python_Expression trueExpr = Python_BuiltIn.generateBuiltIn(EagleGenerator.BuiltInEnum.TRUE, null);
			Python_ExpressionStatement asgExprStmt = Python_Assignment.generateAssignment(oddName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, trueExpr, null, source);
			Python_ComplexStatement asgStmt = Python_Generator.wrapStatement(asgExprStmt);

			List<Python_ComplexStatement> copyActions = new List<Python_ComplexStatement>();
			copyActions.Add(asgStmt);
			foreach (AbstractStatement act in actions)
			{
				copyActions.Add((Python_ComplexStatement) act);
			}
			return generateWhileMany(whileCond, copyActions, source);
		}

		public static Python_ComplexStatement generateWhileOne(Python_Expression cond, Python_ComplexStatement action, AbstractToken source)
		{
			List<Python_ComplexStatement> actions = new List<Python_ComplexStatement>();
			actions.Add(action);
			return generateWhileMany(cond, actions, source);
		}

		public static Python_ComplexStatement generateWhileMany(Python_Expression cond, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			Python_WhileStatement doStmt = new Python_WhileStatement();
			doStmt.condition = cond;
			doStmt.colon = new PunctuationColon();

			doStmt.statements = new Python_StatementBlock();
			Python_MultilineStatement multi = new Python_MultilineStatement();
			multi.statements = new TokenList<Python_ComplexStatement>();
			doStmt.statements.setWhich(multi);

			foreach (Python_ComplexStatement stmt in actions)
			{
				multi.statements.addToken(stmt);

				// If the parent block gets the 'while' as the parent, line numbers in the
				// side-by-side will pick up the 'while' instead of the first statement.
				if (doStmt.getTransformationSource() == null)
				{
					doStmt.setTransformationSource(stmt.getTransformationSource());
				}
			}

			doStmt.setTransformationSource(source);
			return Python_Generator.wrapStatement(doStmt);
		}
	}

}
