// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class COBOL_PerformInline extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @OPT TokenList<COBOL_PerformClause> clauseList;
	public @S(20) TokenList<COBOL_StatementOrComment> statements;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		COBOL_PerformVarying varyingClause = null;
		COBOL_PerformUntil untilClause = null;
		int i = 0;
		int incr = 0;

		for (COBOL_PerformClause clause : clauseList._elements)
		{
			AbstractToken which = clause.getWhich();
			if (which instanceof COBOL_PerformVarying)
			{
				varyingClause = (COBOL_PerformVarying) which;
				i = interpreter.getIntValue(varyingClause.from);
				incr = interpreter.getIntValue(varyingClause.by);
			}
			else if (which instanceof COBOL_PerformUntil)
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
				boolean stop = interpreter.getBoolValue(untilClause.condition);
				if (stop) break;
			}

			metric.iterate();

			for (COBOL_StatementOrComment sentence : statements._elements)
			{
				result = interpreter.tryToInterpret(sentence.getWhich());
				if (result != Eagle_Statement_Result.NORMAL) break;
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

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		String indexVar = null;
		AbstractExpression initExpr = null;
		AbstractExpression incrExpr = null;
		AbstractExpression whileExpr = null;

		TokenList<COBOL_PerformClause> clauses = this.clauseList;
		if (clauses != null)
		{
			for (COBOL_PerformClause clause : clauses._elements)
			{
				AbstractToken which = clause.getWhich();
				if (which instanceof COBOL_PerformVarying)
				{
					COBOL_PerformVarying varying = (COBOL_PerformVarying) which;
					indexVar = varying.id.getValue();
					AbstractExpression fromExpr = transformer.transformExpression(generator, varying.from);
					initExpr = generator.newAssignmentExpression(indexVar, SubscriptEnum.FIRST_IS_ONE,
							null, AssignmentEnum.EQUALS, fromExpr, which);
					AbstractExpression byExpr = transformer.transformExpression(generator, varying.by);
					incrExpr = generator.newAssignmentExpression(indexVar, SubscriptEnum.FIRST_IS_ONE,
							null, AssignmentEnum.PLUS_EQUALS, byExpr, which);
				}
				else if (which instanceof COBOL_PerformUntil)
				{
					COBOL_PerformUntil until = (COBOL_PerformUntil) which;
					AbstractExpression untilExpr = transformer.transformExpression(generator, until.condition);
					whileExpr = generator.newNotExpression(untilExpr, which);
				}
			}
		}

		ArrayList<AbstractStatement> stmts = new ArrayList<AbstractStatement>();
		for (COBOL_StatementOrComment stmtOrComm : statements._elements)
		{
			ArrayList<AbstractStatement> newStmts = transformer.transformStatement(
					generator, stmtOrComm.getWhich());
			for (AbstractStatement stmt : newStmts)
			{
				stmts.add(stmt);
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