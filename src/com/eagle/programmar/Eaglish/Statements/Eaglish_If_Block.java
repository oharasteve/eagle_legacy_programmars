// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Eaglish_If_Block extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Eaglish_Keyword IF = new Eaglish_Keyword("IF");
	public @S(20) Eaglish_Expression condition;
	public @S(30) Eaglish_EndOfLine eoln1;
	public @S(40) @OPT TokenList<Eaglish_Statement> statements;
	public @S(50) @OPT TokenList<Eaglish_If_ElseIf_Block> elseifBlocks;
	public @S(60) @OPT Eaglish_If_Else_Block elseBlock;
	public @S(70) Eaglish_Keyword END_IF = new Eaglish_Keyword("END_IF");
	public @S(80) Eaglish_EndOfLine eoln2;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Eaglish_If_ElseIf_Block extends TokenSequence
	{
		public @S(10) Eaglish_Keyword ELSE_IF = new Eaglish_Keyword("ELSE_IF");
		public @S(20) Eaglish_Expression condition;
		public @S(30) Eaglish_EndOfLine eoln1;
		public @S(40) @OPT TokenList<Eaglish_Statement> statements;
	}

	public static class Eaglish_If_Else_Block extends TokenSequence
	{
		public @S(10) Eaglish_Keyword ELSE = new Eaglish_Keyword("ELSE");
		public @S(20) Eaglish_EndOfLine eoln1;
		public @S(30) @OPT TokenList<Eaglish_Statement> statements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Eaglish_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			
			if (elseifBlocks != null)
			{
				for (Eaglish_If_ElseIf_Block elif : elseifBlocks._elements)
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
			if (elseifBlocks != null)
			{
				for (Eaglish_If_ElseIf_Block elif : elseifBlocks._elements)
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
			for (Eaglish_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}
}
