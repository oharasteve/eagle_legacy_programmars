// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_SingleOrMultiLineStatement;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class FSharp_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("conditional-expressions-if-then-else") FSharp_Keyword IF = new FSharp_Keyword("if");
	public @S(20) FSharp_Expression condition;
	public @S(30) FSharp_Keyword THEN = new FSharp_Keyword("then");
	public @S(40) FSharp_SingleOrMultiLineStatement ifThen;
	public @S(50) @OPT TokenList<FSharp_IfElif> ifElifs;
	public @S(60) @OPT FSharp_IfElse ifElse;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class FSharp_IfElif extends TokenSequence
	{
		public @S(10) FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) FSharp_Keyword ELIF = new FSharp_Keyword("elif");
		public @S(30) FSharp_Expression condition;
		public @S(40) FSharp_SingleOrMultiLineStatement elifStatement;
	}

	public static class FSharp_IfElse extends TokenSequence
	{
		public @S(10) FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) FSharp_Keyword ELSE = new FSharp_Keyword("else");
		public @S(30) FSharp_SingleOrMultiLineStatement ifElseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		FSharp_SingleOrMultiLineStatement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
			
			if (ifElifs != null)
			{
				for (FSharp_IfElif elif : ifElifs._elements)
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
			todo = ifThen;
		}
		else
		{
			int seq = 1;
			// Check for each 'else if'
			if (ifElifs != null)
			{
				for (FSharp_IfElif elif : ifElifs._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.elifStatement;
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
					todo = ifElse.ifElseStatement;
				}
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo.getWhich());
		}

		return result;
	}
}
