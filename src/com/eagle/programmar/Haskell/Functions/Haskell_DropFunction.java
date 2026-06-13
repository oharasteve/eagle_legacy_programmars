// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 12, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_DropFunction extends PrimaryOperator
		implements EagleRunnable // , EagleTransformableExpression
{
	public @S(10) Haskell_Keyword DROP = new Haskell_Keyword("drop");
	public @S(20) Haskell_Expression scExpr;
	public @S(30) Haskell_Expression strExpr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int sc = interpreter.getIntValue(scExpr);
		String str = interpreter.getStrValue(strExpr);
		interpreter.pushStr(str.substring(sc));
	}

//	@Override
//	public AbstractExpression transformExpression(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
//		return generator.newStringFunction(null, theExpr, this);
//	}
}
