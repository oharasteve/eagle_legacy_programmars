// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_CondFunction extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_cond.htm") Lisp_KeywordChoice COND = new Lisp_KeywordChoice("cond");
	public @S(30) TokenList<Lisp_CondPair> pairs;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Lisp_CondPair extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Lisp_Expression condition;
		public @S(30) TokenList<Lisp_Expression> values;
		public @S(40) PunctuationRightParen rightParen;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();

			for (int i = 0; i < pairs.size(); i++)
			{
				Lisp_CondPair pair = pairs._elements.get(i);
				_metrics.add(new IfCondMetrics(interpreter._metrics, pair));
			}
		}

		// Perform action
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		int seq = 0;
		for (Lisp_CondPair condPair : pairs._elements)
		{
			boolean cond = interpreter.getBoolValue(condPair.condition);
			_metrics.get(seq).completedIf(cond);
			if (cond)
			{
				// Rarely will there be more than one value per pair
				for (Lisp_Expression expr : condPair.values._elements)
				{
					result = interpreter.tryToInterpret(expr);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
				break;
			}
			seq++;
		}
		return result;
	}
}
