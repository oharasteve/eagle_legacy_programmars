// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Ruby_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("control_expressions_rdoc.html#label-if+Expression") Ruby_Keyword IF = new Ruby_Keyword("if");
	public @S(20) Ruby_Expression condition;
	public @S(30) Ruby_EOLN eoln1;
	public @S(40) TokenList<Ruby_Statement> thenStatements;
	public @S(50) @OPT Ruby_IfElseClause elseClause;
	public @S(60) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(70) Ruby_EOLN eoln2;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Ruby_IfElseClause extends TokenSequence
	{
		public @S(10) Ruby_Keyword ELSE = new Ruby_Keyword("else");
		public @S(20) @OPT Ruby_EOLN eoln2;
		public @S(30) TokenList<Ruby_Statement> elseStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Ruby_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.getFileName(),
						elseClause.getStartLine(), elseClause.getStartChar()));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatements;
		}
		else
		{
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatements;
			}
		}

		if (todo != null)
		{
			for (Ruby_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}
}
