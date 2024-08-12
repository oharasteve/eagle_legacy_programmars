// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DoFunction extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) PunctuationLeftParen leftParen1;
	public @S(20) @DOC("s_do.htm") Lisp_Keyword DO = new Lisp_Keyword("do");
	public @S(30) PunctuationLeftParen leftParen2;
	public @S(40) PunctuationLeftParen leftParen3;
	public @S(50) Lisp_Variable_Definition var;
	public @S(60) Lisp_SExpr initialValue;
	public @S(70) Lisp_SExpr increment;
	public @S(80) PunctuationRightParen rightParen3;
	public @S(90) PunctuationRightParen rightParen2;
	public @S(100) PunctuationLeftParen leftParen4;
	public @S(110) Lisp_SExpr terminateCondition;
	public @S(120) PunctuationRightParen rightParen4;
	public @S(130) TokenList<Lisp_SExpr> actions;
	public @S(140) PunctuationRightParen rightParen1;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		EagleValue val = interpreter.getEagleValue(initialValue);
		interpreter.setSymbol(var, var.getValue(), val);
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean done = interpreter.getBoolValue(terminateCondition);
			if (done) break;
			
			metric.iterate();

			for (Lisp_SExpr action : actions._elements)
			{
				result = interpreter.tryToInterpret(action);
				
				if (result == Eagle_Statement_Result.RETURN)
				{
					metric.broke();
				}

				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			if (result != Eagle_Statement_Result.NORMAL) break;
			val = interpreter.getEagleValue(increment);
			interpreter.setSymbol(increment, var.getValue(), val);
		}
		
		_metrics.competedLoop(metric);

		interpreter.removeSymbol(var.getValue());
		return Eagle_Statement_Result.NORMAL;
	}
}
