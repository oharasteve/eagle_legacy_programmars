// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_IfStatement extends COBOL_AbstractStatement implements EagleRunnableWithResult
{
	public @S(10) COBOL_Keyword IF = new COBOL_Keyword("IF");
	public @S(20) COBOL_Expression condition;
	public @S(30) @OPT COBOL_Keyword THEN = new COBOL_Keyword("THEN");
	public @S(40) TokenList<COBOL_StatementOrComment> thenActions;
	public @S(50) @OPT COBOL_Else elseClause;
	public @S(60) @OPT COBOL_Keyword ENDIF = new COBOL_Keyword("END-IF");

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class COBOL_Else extends TokenSequence
	{
		public @S(10) COBOL_Keyword ELSE = new COBOL_Keyword("ELSE");
		public @S(20) TokenList<COBOL_StatementOrComment> elseActions;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
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
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			for (COBOL_StatementOrComment stmt : thenActions._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}
		else if (elseClause != null && elseClause.isPresent())
		{
			_metrics.get(1).completedIf(true);
			for (COBOL_StatementOrComment stmt : elseClause.elseActions._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}
}
