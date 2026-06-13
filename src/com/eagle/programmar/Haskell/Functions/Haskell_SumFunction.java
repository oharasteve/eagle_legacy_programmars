// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 12, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Expressions.Haskell_BracketsExpression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_SumFunction extends PrimaryOperator
		implements EagleRunnable // , EagleTransformableExpression
{
	public @S(10) Haskell_Keyword SUM = new Haskell_Keyword("sum");
	public @S(20) Haskell_BracketsExpression values;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int sum = 0;
		int nValues = values.values.getPrimaryCount();
		for (int i = 0; i < nValues; i++)
		{
			Haskell_Expression expr = values.values.getPrimaryElement(i);
			sum += interpreter.getIntValue(expr);
		}
		interpreter.pushInt(sum);
	}

//	@Override
//	public AbstractExpression transformExpression(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
//		return generator.newLengthFunction(theExpr, this);
//	}
}
