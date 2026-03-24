// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.VB.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_ArrayExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Keyword ARRAY = new VB_Keyword("Array");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<VB_Expression, PunctuationComma> expressions;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
			vals.addValue(val);
			// if (interpreter._TRACE) System.err.println("*** array += " + val.toString());
		}

		interpreter.pushEagleValue(vals);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			exprs.add(transformer.transformExpression(generator, expressions.getPrimaryElement(i)));
		}
		return generator.newArrayExpression(exprs, this);
	}
}