// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 2, 2026

package com.eagle.programmar.Haskell.Expressions;

import com.eagle.generate.BuiltInEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Terminals.Haskell_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Haskell_BuiltInExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Haskell_KeywordChoice builtinConstant = new Haskell_KeywordChoice("False", "True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.getValue())
		{
		case "False":
			interpreter.pushBool(false);
			return;
		case "True":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than True/False: " + builtinConstant);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		BuiltInEnum builtIn;
		switch (builtinConstant.getValue())
		{
		case "False":
			builtIn = BuiltInEnum.FALSE;
			break;
		case "True":
			builtIn = BuiltInEnum.TRUE;
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtinConstant);
		}
		return generator.newBuiltInExpression(builtIn, this);
	}
}
