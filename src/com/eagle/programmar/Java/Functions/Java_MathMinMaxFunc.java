// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 3, 2025

package com.eagle.programmar.Java.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MathMinMaxFunc extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Java_KeywordChoice MINMAX = new Java_KeywordChoice("min", "max");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE SeparatedList<Java_Expression,PunctuationComma> expressions;
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
	
	public Java_Expression generateMinMax(boolean isMin,
			ArrayList<Java_Expression> exprs, AbstractToken source)
	{
		this.MINMAX.setValue(isMin ? "min" : "max");
		this.leftParen = new PunctuationLeftParen();
		this.expressions = new SeparatedList<Java_Expression, PunctuationComma>();
		this.expressions.addPrimaryElement(exprs.get(0));
		for (int i = 1; i < exprs.size(); i++)
		{
			this.expressions.addSecondaryElement(new PunctuationComma());
			this.expressions.addPrimaryElement(exprs.get(i));
		}
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
