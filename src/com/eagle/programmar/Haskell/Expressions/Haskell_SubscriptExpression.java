// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2026

package com.eagle.programmar.Haskell.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Haskell_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable // , EagleTransformableExpression
{
	public @S(10) Haskell_Expression left = new Haskell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Haskell_Punctuation subscrOperator = new Haskell_Punctuation("!!");
	public @S(30) Haskell_Expression right = new Haskell_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArrayList<EagleValue> array = interpreter.getArrayValue(left);
		int index = interpreter.getIntValue(right);
		interpreter.pushEagleValue(array.get(index));
	}

//	@Override
//	public AbstractExpression transformExpression(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
//		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
//		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
//	}
}
