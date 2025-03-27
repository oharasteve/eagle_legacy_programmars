// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Statement;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("#Conditional-Constructs") Bash_Keyword IF = new Bash_Keyword("if");
	public @S(20) Bash_Condition condition;
	public @S(30) Bash_EndOfLine eoln1;
	public @S(40) Bash_Keyword THEN = new Bash_Keyword("then");
	public @S(50) @OPT Bash_EndOfLine eoln2;
	public @S(60) TokenList<Bash_Statement> statements;
	public @S(70) @OPT TokenList<Bash_If_Elif> elseIfBlock;
	public @S(80) @OPT Bash_If_Else elseBlock;
	public @S(90) Bash_Keyword FI = new Bash_Keyword("fi");

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Bash_If_Elif extends TokenSequence
	{
		public @S(10) Bash_Keyword ELIF = new Bash_Keyword("elif");
		public @S(20) Bash_Condition condition;
		public @S(30) Bash_EndOfLine eoln1;
		public @S(40) Bash_Keyword THEN = new Bash_Keyword("then");
		public @S(50) @OPT Bash_EndOfLine eoln2;
		public @S(60) TokenList<Bash_Statement> statements;
	}

	public static class Bash_If_Else extends TokenSequence
	{
		public @S(10) Bash_Keyword ELSE = new Bash_Keyword("else");
		public @S(20) @OPT Bash_EndOfLine eoln;
		public @S(30) TokenList<Bash_Statement> statements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Bash_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			
			if (elseIfBlock != null)
			{
				for (Bash_If_Elif elif : elseIfBlock._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif));
				}
			}
			
			if (elseBlock != null && elseBlock.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseBlock));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = statements;
		}
		else
		{
			int seq = 1;
			// Check for each 'else if'
			if (elseIfBlock != null)
			{
				for (Bash_If_Elif elif : elseIfBlock._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.statements;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (elseBlock != null && elseBlock.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = elseBlock.statements;
				}
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			for (Bash_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}
}
