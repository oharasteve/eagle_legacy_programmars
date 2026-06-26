// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/for.html") TCL_Keyword FOR = new TCL_Keyword("for");
	public @S(20) PunctuationLeftBrace leftBrace1;
	public @S(30) TCL_SetStatement initialize;
	public @S(40) PunctuationRightBrace rightBrace1;
	public @S(50) PunctuationLeftBrace leftBrace2;
	public @S(60) TCL_Expression condition;
	public @S(70) PunctuationRightBrace rightBrace2;
	public @S(80) PunctuationLeftBrace leftBrace3;
	public @S(90) TCL_IncrStatement increment;
	public @S(100) PunctuationRightBrace rightBrace3;
	public @S(110) TCL_Statement action;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(initialize);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();

			result = interpreter.tryToInterpret(action);

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

			interpreter.tryToInterpret(increment);
		}

		// Have to guess to see if it was backwards
		boolean backwards = guessDirection(condition, increment);

		_metrics.completedLoop(metric, backwards);
		return result;
	}

	private static boolean guessDirection(TCL_Expression testExpr, TCL_IncrStatement incrStmt)
	{
		if (incrStmt.amount != null && incrStmt.amount.isPresent())
		{
			AbstractToken which1 = incrStmt.amount.getWhich();
			if (which1 instanceof TCL_Number)
			{
				TCL_Number num = (TCL_Number) which1;
				if (num.getValue().startsWith("-"))
				{
					return true;
				}
			}
		}

		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof TCL_RelationalExpression)
		{
			TCL_RelationalExpression rel = (TCL_RelationalExpression) which2;
			String oper = rel.operator.getWhich().toString().toLowerCase();
			if (oper.equals(">") || oper.equals(">=") || oper.equals("gt") || oper.equals("ge"))
			{
				return true;
			}
		}

		return false; // Just don't know :(
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression initExpr = initialize.transformExpression(transformer, generator);
		AbstractExpression termCond = transformer.transformExpression(generator, condition);
		AbstractExpression incrExpr = increment.transformExpression(transformer, generator);

		ArrayList<AbstractStatement> whileTrue = new ArrayList<AbstractStatement>();

		ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, action.getWhich());
		if (stmts != null)
		{
			for (AbstractStatement stmt : stmts)
			{
				whileTrue.add(stmt);
			}
		}

		return generator.newForLoopStatement(initExpr, null, termCond, incrExpr, whileTrue, this);
	}
}
