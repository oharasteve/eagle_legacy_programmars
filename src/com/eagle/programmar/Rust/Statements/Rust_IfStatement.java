// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/if-expr.html") Rust_Keyword IF = new Rust_Keyword("if");
	public @S(20) Rust_Expression condition;
	public @S(30) Rust_Statement thenStatement;
	public @S(40) @OPT Rust_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Rust_IfElseClause extends TokenSequence implements AbstractStatement
	{
		public @S(10) Rust_Keyword ELSE = new Rust_Keyword("else");
		public @S(20) Rust_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Rust_Statement todo = null;

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

		boolean cond1 = interpreter.getBoolValue(condition);
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
			for (AbstractStatement stmt : transformer.transformStatement(generator,
					elseClause.elseStatement.getWhich()))
			{
				ifFalse.add(stmt);
			}
		}

		return generator.newIfStatement(cond, ifTrue, ifFalse, this);
	}
}
