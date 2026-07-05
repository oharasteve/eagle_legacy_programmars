// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 3, 2025

package com.eagle.programmar.Python.Functions;

import java.util.ArrayList;

import com.eagle.generate.MinMaxEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_MinMax_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_KeywordChoice MINMAX = new Python_KeywordChoice("min", "max");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE SeparatedList<Python_Expression, PunctuationComma> expressions;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int result = interpreter.getIntValue(expressions.first());
		boolean isMin = true;
		if (MINMAX.getValue().equals("max"))
		{
			isMin = false;
		}

		for (int i = 1; i < expressions.getPrimaryCount(); i++)
		{
			int next = interpreter.getIntValue(expressions.getPrimaryElement(i));
			if (isMin)
			{
				if (next < result)
				{
					result = next;
				}
			}
			else // isMax
			{
				if (next > result)
				{
					result = next;
				}
			}
		}
		interpreter.pushInt(result);
	}

	public static Python_Expression generateMinMax2(MinMaxEnum minmax, Python_Expression x1, Python_Expression x2, AbstractToken source)
	{
		ArrayList<Python_Expression> exprs = new ArrayList<Python_Expression>();
		exprs.add(x1);
		exprs.add(x2);
		return generateMinMax(minmax, exprs, source);
	}
	
	public static Python_Expression generateMinMax(MinMaxEnum minmax,
			ArrayList<Python_Expression> exprs, AbstractToken source)
	{
		Python_MinMax_Function mm = new Python_MinMax_Function();
		switch (minmax)
		{
		case MIN:
			mm.MINMAX.setValue("min");
			break;
		case MAX:
			mm.MINMAX.setValue("max");
			break;
		default:
			throw new RuntimeException("Unexpected min/max: " + minmax.toString());
		}
		mm.leftParen = new PunctuationLeftParen();
		mm.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
		mm.expressions.addPrimaryElement(exprs.get(0));
		for (int i = 1; i < exprs.size(); i++)
		{
			mm.expressions.addSecondaryElement(new PunctuationComma());
			mm.expressions.addPrimaryElement(exprs.get(i));
		}
		mm.rightParen = new PunctuationRightParen();

		mm.setTransformationSource(source);
		return Python_Generator.wrapExpression(mm);
	}
}
