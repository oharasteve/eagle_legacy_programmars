// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword FOR = new Algol68_Keyword("FOR");
	public @S(20) Algol68_Variable var;
	public @S(30) TokenList<Algol68_ForClause> clauses;
	public @S(40) Algol68_Keyword DO = new Algol68_Keyword("DO");
	public @S(50) TokenList<Algol68_Statement> statements;
	public @S(60) Algol68_Keyword OD = new Algol68_Keyword("OD");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Algol68_ForClause extends TokenSequence
	{
		public @S(10) Algol68_KeywordChoice FROM = new Algol68_KeywordChoice("FROM", "BY", "TO", "WHILE");
		public @S(20) Algol68_Expression expr;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		boolean haveFrom = false;
		boolean haveTo = false;
		int start = 0;
		int stop = 0;
		int by = 1;
		Algol68_Expression whileExpr = null;
		
		for (Algol68_ForClause clause : clauses._elements)
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
		
		if (!haveFrom) throw new RuntimeException("FOR FROM is required");
		if (!haveTo) throw new RuntimeException("FOR TO is required");
		
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (by > 0 && i > stop) break;
			if (by < 0 && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

			for (Algol68_Statement stmt : statements._elements)
			{
				if (whileExpr != null)
				{
					boolean whileResult = interpreter.getBoolValue(whileExpr);
					if (!whileResult)
					{
						result = Eagle_Statement_Result.BREAK;
						break;
					}
				}

				result = interpreter.tryToInterpret(stmt);
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

			i += by;
		}

		_metrics.competedLoop(metric, by < 0);
		return result;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression startExpr = null;
		AbstractExpression endExpr = null;
		AbstractExpression byExpr = null;
		AbstractExpression whileExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_EQUALS;
		
		String varName = var.vars.first().getValue();
		for (Algol68_ForClause clause : clauses._elements)
		{
			switch (clause.FROM.getValue())
			{
			case "BY":
				AbstractToken which = clause.expr.getWhich();
				if (which instanceof Algol68_NegativeExpression)
				{
					relOp = RelationalEnum.GREATER_EQUALS;
				}
				if (which instanceof Algol68_Number)
				{
					Algol68_Number num = (Algol68_Number) which;
					if (num.getValue().startsWith("-"))
					{
						relOp = RelationalEnum.GREATER_EQUALS;
					}
				}
				AbstractExpression incrExpr = transformer.transformExpression(generator, clause.expr);
				byExpr = generator.newAssignmentExpression(varName,
						SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.PLUS_EQUALS, incrExpr, clause.expr);
				break;
			case "FROM":
				AbstractExpression initExpr = transformer.transformExpression(generator, clause.expr);
				startExpr = generator.newAssignmentExpression(varName,
						SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.EQUALS, initExpr, clause.expr);
				break;
			case "TO":
				endExpr = transformer.transformExpression(generator, clause.expr);
				break;
			case "WHILE":
				whileExpr = transformer.transformExpression(generator, clause.expr);
				break;
			}
		}
		
		if (startExpr == null) throw new RuntimeException("FOR FROM is required");
		if (endExpr == null) throw new RuntimeException("FOR TO is required");
		
		if (byExpr == null)
		{
			AbstractExpression oneExpr = generator.newNumberExpression("1", null);
			byExpr = generator.newAssignmentExpression(var.vars.first().getValue(),
					SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.PLUS_EQUALS, oneExpr, null);
		}
		
		AbstractExpression varExpr = generator.newVariableExpression(varName,
				SubscriptEnum.FIRST_IS_ONE, null, null);
		AbstractExpression stopExpr = generator.newRelationalExpression(null, varExpr,
				relOp, endExpr, null);

		if (whileExpr != null)
		{
			stopExpr = generator.newLogicalAndExpression(stopExpr, whileExpr, null);
		}

		ArrayList<AbstractStatement> whileTrue = new ArrayList<AbstractStatement>();
		for (Algol68_Statement statement : statements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, statement.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt : stmts)
				{
					whileTrue.add(stmt);
				}
			}
		}
		
		return generator.newForLoopStatement(startExpr, stopExpr, byExpr, whileTrue, this);
	}
}
