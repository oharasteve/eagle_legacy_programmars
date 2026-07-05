// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Haskell_IsPrefixOfFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Haskell_Keyword ISPREFIXOF = new Haskell_Keyword("isPrefixOf");
	public @S(20) Haskell_Expression prefix;
	public @S(30) Haskell_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String pre = interpreter.getStrValue(prefix);
		String value = interpreter.getStrValue(expr);
		interpreter.pushBool(value.startsWith(pre));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression thePre = transformer.transformExpression(generator, prefix);
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newStartsWithFunction(theExpr, thePre, null, null, this);
	}
}
