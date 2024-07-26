// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("compound_stmts.html#the-if-statement") @NOSPACE Python_Keyword IF = new Python_Keyword("if");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) Python_StatementBlock ifThenStatements;
	public @S(50) @OPT TokenList<Python_IfElif> ifElif;
	public @S(60) @OPT Python_IfElse ifElse;
	public @S(70) @OPT TokenList<Python_Comment> comments;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Python_IfElif extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) @NOSPACE Python_Keyword ELIF = new Python_Keyword("elif");
		public @S(40) Python_Expression condition;
		public @S(50) @NOSPACE PunctuationColon colon;
		public @S(60) Python_StatementBlock elifStatements;
	}

	public static class Python_IfElse extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) @NOSPACE Python_Keyword ELSE = new Python_Keyword("else");
		public @S(40) @NOSPACE PunctuationColon colon;
		public @S(50) Python_StatementBlock ifElseStatements;
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Python_StatementBlock todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
			
			if (ifElif != null)
			{
				for (Python_IfElif elif : ifElif._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif.getFileName(), elif.getStartLine(),
							elif.getStartChar()));
				}
			}
			
			if (ifElse != null && ifElse.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, ifElse.getFileName(), ifElse.getStartLine(),
						ifElse.getStartChar()));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = ifThenStatements;
		}
		else
		{
			int seq = 1;
			// Check for each 'else if'
			if (ifElif != null)
			{
				for (Python_IfElif elif : ifElif._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.elifStatements;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (ifElse != null && ifElse.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = ifElse.ifElseStatements;
				}
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
}
