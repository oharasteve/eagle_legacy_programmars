// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

namespace com.eagle.programmar.COBOL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using COBOL_StatementOrComment = com.eagle.programmar.COBOL.COBOL_StatementOrComment;
	using COBOL_PerformUntil = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
	using COBOL_PerformVarying = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_PerformInline : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<COBOL_PerformClause> clauseList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_StatementOrComment> statements;
		public TokenList<COBOL_StatementOrComment> statements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			COBOL_PerformVarying varyingClause = null;
			COBOL_PerformUntil untilClause = null;
			int i = 0;
			int incr = 0;

			foreach (COBOL_PerformClause clause in clauseList._elements)
			{
				AbstractToken which = clause.getWhich();
				if (which is COBOL_PerformVarying)
				{
					varyingClause = (COBOL_PerformVarying) which;
					i = interpreter.getIntValue(varyingClause.from);
					incr = interpreter.getIntValue(varyingClause.by);
				}
				else if (which is COBOL_PerformUntil)
				{
					untilClause = (COBOL_PerformUntil) which;
				}
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, this);
			}
			ForLoopMetric metric = new ForLoopMetric();

			// Evaluate the paragraph
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				if (varyingClause != null)
				{
					interpreter.setSymbol(varyingClause, varyingClause.id.getValue(), new EagleInteger(i));
				}
				if (untilClause != null)
				{
					bool stop = interpreter.getBoolValue(untilClause.condition);
					if (stop)
					{
						break;
					}
				}

				metric.iterate();

				foreach (COBOL_StatementOrComment sentence in statements._elements)
				{
					result = interpreter.tryToInterpret(sentence.getWhich());
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

				if (varyingClause != null) // Don't need this test really
				{
					i += incr;
				}
			}

			_metrics.competedLoop(metric, incr < 0);
			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string indexVar = null;
			AbstractExpression initExpr = null;
			AbstractExpression incrExpr = null;
			AbstractExpression whileExpr = null;

			TokenList<COBOL_PerformClause> clauses = this.clauseList;
			if (clauses != null)
			{
				foreach (COBOL_PerformClause clause in clauses._elements)
				{
					AbstractToken which = clause.getWhich();
					if (which is COBOL_PerformVarying)
					{
						COBOL_PerformVarying varying = (COBOL_PerformVarying) which;
						indexVar = varying.id.getValue();
						AbstractExpression fromExpr = transformer.transformExpression(generator, varying.from);
						initExpr = generator.newAssignmentExpression(indexVar, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, which);
						AbstractExpression byExpr = transformer.transformExpression(generator, varying.by);
						incrExpr = generator.newAssignmentExpression(indexVar, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, byExpr, which);
					}
					else if (which is COBOL_PerformUntil)
					{
						COBOL_PerformUntil until = (COBOL_PerformUntil) which;
						AbstractExpression untilExpr = transformer.transformExpression(generator, until.condition);
						whileExpr = generator.newLogicalNotExpression(untilExpr, which);
					}
				}
			}

			List<AbstractStatement> stmts = new List<AbstractStatement>();
			foreach (COBOL_StatementOrComment stmtOrComm in statements._elements)
			{
				List<AbstractStatement> newStmts = transformer.transformStatement(generator, stmtOrComm.getWhich());
				foreach (AbstractStatement stmt in newStmts)
				{
					stmts.Add(stmt);
				}
			}

			// Four cases: both varying and while; just varying; just while; neither
			if (initExpr != null)
			{
				return generator.newForLoopStatement(initExpr, whileExpr, incrExpr, stmts, this);
			}
			if (whileExpr == null)
			{
				return generator.newBlockStatement(stmts, this);
			}
			return generator.newWhileStatement(whileExpr, stmts, this);
		}
	}
}
