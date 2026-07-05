// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class PLI_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @OPT PLI_Label label;
	public @S(20) @DOC("7.27") PLI_Keyword IF = new PLI_Keyword("IF");
	public @S(30) PLI_Expression condition;
	public @S(40) PLI_Keyword THEN = new PLI_Keyword("THEN");
	public @S(50) @OPT TokenList<PLI_Comment> comment1;
	public @S(60) PLI_Statement thenStatement;
	public @S(70) @OPT TokenList<PLI_Comment> comment2;
	public @S(80) @OPT PLI_Else elseClause;
	public @S(90) @OPT PLI_Keyword ENDIF = new PLI_Keyword("END-IF");

	public static class PLI_Else extends TokenSequence
	{
		public @S(10) PLI_Keyword ELSE = new PLI_Keyword("ELSE");
		public @S(20) @OPT TokenList<PLI_Comment> comment3;
		public @S(30) PLI_Statement elseStatement;
		public @S(40) @OPT TokenList<PLI_Comment> comment4;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		PLI_Statement todo;

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
			todo = null;

			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatement;
			}
		}

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
		if (stmts != null)
		{
			for (AbstractStatement stmt2 : stmts)
			{
				ifTrue.add(stmt2);
			}
		}

		if (this.elseClause != null && this.elseClause.isPresent())
		{
			for (AbstractStatement stmt4 : transformer.transformStatement(generator,
					elseClause.elseStatement.getWhich()))
			{
				ifFalse.add(stmt4);
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
