// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Go_KeywordChoice builtinConstant = new Go_KeywordChoice("false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "false":
			interpreter.pushBool(false);
			return;
		case "true":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		BuiltInEnum builtIn;
		switch (builtinConstant.toString())
		{
		case "false":
			builtIn = BuiltInEnum.FALSE;
			break;
		case "true":
			builtIn = BuiltInEnum.TRUE;
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtinConstant);
		}
		return generator.newBuiltInExpression(builtIn, this);
	}
}
