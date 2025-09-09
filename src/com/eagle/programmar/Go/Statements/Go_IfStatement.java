// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("#If_statements") Go_Keyword IF = new Go_Keyword("if");
	public @S(20) Go_Expression condition;
	public @S(30) Go_Statement thenStatement;
	public @S(40) @OPT Go_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Go_IfElseClause extends TokenSequence
	{
		public @S(10) Go_Keyword ELSE = new Go_Keyword("else");
		public @S(20) Go_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Go_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatement;
		}
		else
		{
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatement;
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();
		
		ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
		if (stmts != null)
		{
			for (AbstractStatement stmt : stmts)
			{
				ifTrue.add(stmt);
			}
		}
		
		if (elseClause != null && elseClause.isPresent())
		{
			for (AbstractStatement stmt : transformer.transformStatement(generator, elseClause.elseStatement.getWhich()))
			{
				ifFalse.add(stmt);
			}
		}
		
		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
