// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;

public class Haskell_ModFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Haskell_Keyword MOD = new Haskell_Keyword("mod");
	public @S(20) Haskell_Expression numer;
	public @S(30) Haskell_Expression denom;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numerInt = interpreter.getIntValue(numer);
		int denomInt = interpreter.getIntValue(denom);
		interpreter.pushInt(Math.floorMod(numerInt, denomInt));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression numerExpr = transformer.transformExpression(generator, numer);
		AbstractExpression denomExpr = transformer.transformExpression(generator, denom);
		return generator.newMultiplicativeExpression(numerExpr, MultiplicativeEnum.MODULUS, denomExpr, this);
	}
}
