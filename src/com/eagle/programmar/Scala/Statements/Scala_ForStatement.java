// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_Subfield;
import com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("taste-control-structures.html#for-loops-and-expressions")
			Scala_Keyword FOR = new Scala_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Variable var;
	public @S(40) Scala_Punctuation arrow = new Scala_Punctuation("<-");
	public @S(50) Scala_Expression values;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) Scala_Statement statement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		AbstractToken which = values.getWhich();
		Scala_RangeExpression range = null;
		boolean backwards = false;
		if (which instanceof Scala_RangeExpression)
		{
			range = (Scala_RangeExpression) which;
		}
		else
		{
			// Could also look like this: (a to b).reverse
			if (which instanceof Scala_Subfield)
			{
				Scala_Subfield sub = (Scala_Subfield) which;
				if (sub.left.getWhich() instanceof Scala_ParenthesizedExpression)
				{
					Scala_ParenthesizedExpression paren = (Scala_ParenthesizedExpression) sub.left.getWhich();
					if (paren.expression.getWhich() instanceof Scala_RangeExpression)
					{
						if (sub.right.getWhich() instanceof Scala_Identifier_Reference)
						{
							Scala_Identifier_Reference id = (Scala_Identifier_Reference) sub.right.getWhich();
							if (id.getValue().equals("reverse"))
							{
								range = (Scala_RangeExpression) paren.expression.getWhich();
								backwards = true;
							}
						}
					}
				}
			}
		}
		
		if (range == null)
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}

		int start = interpreter.getIntValue(range.left);
		int stop = interpreter.getIntValue(range.right);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		if (backwards) i = stop;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < start) break;

			metric.iterate();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.vars.first().getValue(), new EagleInteger(i));

			result = interpreter.tryToInterpret(statement);

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
