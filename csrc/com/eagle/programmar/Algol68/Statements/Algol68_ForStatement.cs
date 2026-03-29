// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Statement = com.eagle.programmar.Algol68.Algol68_Statement;
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using Algol68_NegativeExpression = com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression;
	using Algol68_Keyword = com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using Algol68_Number = com.eagle.programmar.Algol68.Terminals.Algol68_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword FOR = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("FOR");
		public Algol68_Keyword FOR = new Algol68_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Variable var;
		public Algol68_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<Algol68_ForClause> clauses;
		public TokenList<Algol68_ForClause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword DO = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("DO");
		public Algol68_Keyword DO = new Algol68_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Algol68.Algol68_Statement> statements;
		public TokenList<Algol68_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword OD = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("OD");
		public Algol68_Keyword OD = new Algol68_Keyword("OD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationSemicolon semicolon;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public class Algol68_ForClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice FROM = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("FROM", "BY", "TO", "WHILE");
			public Algol68_KeywordChoice FROM = new Algol68_KeywordChoice("FROM", "BY", "TO", "WHILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Expression expr;
			public Algol68_Expression expr;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			bool haveFrom = false;
			bool haveTo = false;
			int start = 0;
			int stop = 0;
			int by = 1;
			Algol68_Expression whileExpr = null;

			foreach (Algol68_ForClause clause in clauses._elements)
			{
				switch (clause.FROM.getValue())
				{
				case "BY":
					by = interpreter.getIntValue(clause.expr);
					break;
				case "FROM":
					start = interpreter.getIntValue(clause.expr);
					haveFrom = true;
					break;
				case "TO":
					stop = interpreter.getIntValue(clause.expr);
					haveTo = true;
					break;
				case "WHILE":
					whileExpr = clause.expr;
					break;
				}
			}

			if (!haveFrom)
			{
				throw new Exception("FOR FROM is required");
			}
			if (!haveTo)
			{
				throw new Exception("FOR TO is required");
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			int i = start;
			while (true)
			{
				if (by > 0 && i > stop)
				{
					break;
				}
				if (by < 0 && i < stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

				foreach (Algol68_Statement stmt in statements._elements)
				{
					if (whileExpr != null)
					{
						bool whileResult = interpreter.getBoolValue(whileExpr);
						if (!whileResult)
						{
							result = Eagle_Statement_Result.BREAK;
							break;
						}
					}

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

				i += by;
			}

			_metrics.competedLoop(metric, by < 0);
			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression startExpr = null;
			AbstractExpression endExpr = null;
			AbstractExpression byExpr = null;
			AbstractExpression whileExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;

			string varName = var.vars.first().getValue();
			foreach (Algol68_ForClause clause in clauses._elements)
			{
				switch (clause.FROM.getValue())
				{
				case "BY":
					AbstractToken which = clause.expr.getWhich();
					if (which is Algol68_NegativeExpression)
					{
						relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					}
					if (which is Algol68_Number)
					{
						Algol68_Number num = (Algol68_Number) which;
						if (num.getValue().StartsWith("-"))
						{
							relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
						}
					}
					AbstractExpression incrExpr = transformer.transformExpression(generator, clause.expr);
					byExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, incrExpr, clause.expr);
					break;
				case "FROM":
					AbstractExpression initExpr = transformer.transformExpression(generator, clause.expr);
					startExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, initExpr, clause.expr);
					break;
				case "TO":
					endExpr = transformer.transformExpression(generator, clause.expr);
					break;
				case "WHILE":
					whileExpr = transformer.transformExpression(generator, clause.expr);
					break;
				}
			}

			if (startExpr == null)
			{
				throw new Exception("FOR FROM is required");
			}
			if (endExpr == null)
			{
				throw new Exception("FOR TO is required");
			}

			if (byExpr == null)
			{
				AbstractExpression oneExpr = generator.newNumberExpression("1", null);
				byExpr = generator.newAssignmentExpression(var.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, oneExpr, null);
			}

			AbstractExpression varExpr = generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, null);
			AbstractExpression stopExpr = generator.newRelationalExpression(null, varExpr, relOp, endExpr, null);

			if (whileExpr != null)
			{
				stopExpr = generator.newLogicalAndExpression(stopExpr, whileExpr, null);
			}

			List<AbstractStatement> whileTrue = new List<AbstractStatement>();
			foreach (Algol68_Statement statement in statements._elements)
			{
				List<AbstractStatement> stmts = transformer.transformStatement(generator, statement.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement stmt in stmts)
					{
						whileTrue.Add(stmt);
					}
				}
			}

			return generator.newForLoopStatement(startExpr, stopExpr, byExpr, whileTrue, this);
		}
	}

}
