// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Rexx.Rexx_Element.Rexx_Statement;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rexx_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("instructions-if") Rexx_Keyword IF = new Rexx_Keyword("IF");
	public @S(20) Rexx_Expression condition;
	public @S(30) Rexx_Keyword THEN = new Rexx_Keyword("THEN");
	public @S(40) Rexx_EndOfLine eoln;
	public @S(50) Rexx_Statement thenStatement;
	public @S(60) @OPT Rexx_IfElseClause elseClause;

	public static class Rexx_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<Rexx_Comment> comments;
		public @S(20) Rexx_EndOfLine eoln1;
		public @S(30) Rexx_Keyword ELSE = new Rexx_Keyword("ELSE");
		public @S(40) Rexx_EndOfLine eoln2;
		public @S(50) Rexx_Statement elseStatement;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		if (cond)
		{
			result = interpreter.tryToInterpret(thenStatement);
		}
		else if (elseClause != null && elseClause.isPresent())
		{
			result = interpreter.tryToInterpret(elseClause.elseStatement);
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
				this.thenStatement.getWhich());
		if (stmts != null)
		{
			for (AbstractStatement stmt : stmts)
			{
				ifTrue.add(stmt);
			}
		}

		if (this.elseClause != null && this.elseClause.isPresent())
		{
			for (AbstractStatement stmt : transformer.transformStatement(generator,
					this.elseClause.elseStatement.getWhich()))
			{
				ifFalse.add(stmt);
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
