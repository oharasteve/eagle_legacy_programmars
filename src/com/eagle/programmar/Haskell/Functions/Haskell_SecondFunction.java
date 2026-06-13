// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 12, 2026

package com.eagle.programmar.Haskell.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_SecondFunction extends PrimaryOperator
		implements EagleRunnable // , EagleTransformableExpression
{
	public @S(10) Haskell_Keyword SND = new Haskell_Keyword("snd");
	public @S(20) Haskell_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArrayList<EagleValue> array = interpreter.getArrayValue(expr);
		interpreter.pushEagleValue(array.get(1));	// Second value
	}

//	@Override
//	public AbstractExpression transformExpression(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
//		return generator.newStringFunction(null, theExpr, this);
//	}
}
