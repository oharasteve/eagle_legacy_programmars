// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_SimpleArray extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Javascript_Expression expr;
	public @S(30) @OPT TokenList<Javascript_MoreArray> more;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class Javascript_MoreArray extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Javascript_Expression expr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		if (expr != null && expr.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expr);
			array.addValue(val);
			for (Javascript_MoreArray nxt : more._elements)
			{
				val = interpreter.getEagleValue(nxt.expr);
				array.addValue(val);
			}
		}
		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		AbstractExpression newExpr1 = transformer.transformExpression(generator, expr);
		exprs.add(newExpr1);
		for (Javascript_MoreArray next : more._elements)
		{
			AbstractExpression newExpr2 = transformer.transformExpression(generator, next.expr);
			exprs.add(newExpr2);
		}
		return generator.newArrayExpression(exprs, this);
	}
}
