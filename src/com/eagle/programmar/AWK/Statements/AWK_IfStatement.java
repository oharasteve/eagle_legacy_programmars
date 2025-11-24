// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class AWK_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("#index-if-statement-2") AWK_Keyword IF = new AWK_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT AWK_EndOfLine eoln;
	public @S(60) AWK_IfBlock blockThen;
	public @S(70) @OPT AWK_IfElse ifelse;

	public static class AWK_IfElse extends TokenSequence implements AbstractStatement
	{
		public @S(10) AWK_Keyword ELSE = new AWK_Keyword("else");
		public @S(20) @OPT AWK_EndOfLine eoln;
		public @S(30) AWK_IfBlock blockElse;
	}

	public static class AWK_IfBlock extends TokenChooser
	{
		public @CHOICE AWK_Statements XXstmt;
		public @CHOICE AWK_Action XXaction;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		AWK_IfBlock todo;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			if (ifelse != null && ifelse.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, ifelse.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = blockThen;
		}
		else
		{
			todo = null;

			// Check for 'else'
			if (ifelse != null && ifelse.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = ifelse.blockElse;
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			if (todo.getWhich() instanceof AWK_Statements)
			{
				AWK_Statements stmts = (AWK_Statements) todo.getWhich();
				for (int i = 0; i < stmts.statements.getPrimaryCount(); i++)
				{
					AWK_Statement stmt = stmts.statements.getPrimaryElement(i);
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}
			else
			{
				AWK_Action action = (AWK_Action) todo.getWhich();
				for (AWK_StatementOrComment stmt : action.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		ArrayList<AbstractStatement> stmts1 = transformer.transformStatement(generator, blockThen.getWhich());
		if (stmts1 != null)
		{
			for (AbstractStatement stmt1 : stmts1)
			{
				ifTrue.add(stmt1);
			}
		}

		if (ifelse != null && ifelse.isPresent())
		{
			ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator,
					ifelse.blockElse.getWhich());
			if (stmts2 != null)
			{
				for (AbstractStatement stmt2 : stmts2)
				{
					ifFalse.add(stmt2);
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
