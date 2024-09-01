// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Expressions.Ruby_ParenthesizedExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_Subfield;
import com.eagle.programmar.Ruby.Functions.Ruby_FunctionCall;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Ruby_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("control_expressions_rdoc.html#label-for+Loop") Ruby_Keyword FOR = new Ruby_Keyword("for");
	public @S(20) Ruby_Variable var;
	public @S(30) Ruby_Keyword IN = new Ruby_Keyword("in");
	public @S(40) Ruby_Expression values;
	public @S(50) Ruby_EOLN eoln1;
	public @S(60) TokenList<Ruby_Statement> statements;
	public @S(70) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(80) Ruby_EOLN eoln2;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		AbstractToken which = values.getWhich();
		boolean backwards = false;
		int start = 0;
		int stop = 0;
		boolean success = false;
		if (which instanceof Ruby_RangeExpression)
		{
			Ruby_RangeExpression range = (Ruby_RangeExpression) which;
			start = interpreter.getIntValue(range.left);
			stop = interpreter.getIntValue(range.right);
			success = true;
		}
		else
		{
			// Could also look like this: (3).downto(1)
			if (which instanceof Ruby_Subfield)
			{
				Ruby_Subfield sub = (Ruby_Subfield) which;
				if (sub.left.getWhich() instanceof Ruby_ParenthesizedExpression)
				{
					Ruby_ParenthesizedExpression paren = (Ruby_ParenthesizedExpression) sub.left.getWhich();
					start = interpreter.getIntValue(paren.expression);
					if (sub.right.getWhich() instanceof Ruby_FunctionCall)
					{
						Ruby_FunctionCall func = (Ruby_FunctionCall) sub.right.getWhich();
						if (func.funcName.vars.first().getValue().equals("downto"))
						{
							stop = interpreter.getIntValue(func.arguments.first());
							backwards = true;
							success = true;
						}
					}
				}
			}
		}
		
		if (!success)
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

			for (Ruby_Statement statement : statements._elements)
			{
				result = interpreter.tryToInterpret(statement);
				if (result != Eagle_Statement_Result.NORMAL) break; 
			}

			if (result == Eagle_Statement_Result.BREAK)
			{
				metric.broke();
				result = Eagle_Statement_Result.NORMAL;
				break;
			}
			else if (result == Eagle_Statement_Result.CONTINUE)
			{
				metric.continued();
				result = Eagle_Statement_Result.NORMAL;
			}
			else if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}

			if (backwards)
				i--;
			else
				i++;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
