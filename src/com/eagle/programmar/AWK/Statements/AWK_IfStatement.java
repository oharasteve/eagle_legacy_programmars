// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
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
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_IfStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @DOC("#index-if-statement-2") AWK_Keyword IF = new AWK_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT AWK_EndOfLine eoln;
	public @S(60) AWK_IfBlock block;
	public @S(70) @OPT AWK_IfElse ifelse;

	public static class AWK_IfElse extends TokenSequence implements AbstractStatement
	{
		public @S(10) AWK_Keyword ELSE = new AWK_Keyword("else");
		public @S(20) @OPT AWK_EndOfLine eoln;
		public @S(30) AWK_IfBlock block;
	}

	public static class AWK_IfBlock extends TokenChooser
	{
		public @CHOICE AWK_Statements stmt;
		public @CHOICE AWK_Action action;
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
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
			if (ifelse.isPresent())
			{
				_metrics.add(
						new IfCondMetrics(interpreter._metrics, ifelse.getFileName(), ifelse.getStartLine(), ifelse.getStartChar()));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = block;
		}
		else
		{
			todo = null;

			// Check for 'else'
			if (ifelse.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = ifelse.block;
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
}
