// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

// NOTE: 'true' & 'false' are NOT part of the Rexx language!
// They are my little extensions that hopefully won't break anything.

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Terminals.Rexx_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.BuiltInEnum;

public class Rexx_BuiltIns extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rexx_KeywordChoice builtinConstant = new Rexx_KeywordChoice(
			"false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString().toLowerCase())
		{
		case "false":
			interpreter.pushBool(false);
			break;
		case "true":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		switch (builtinConstant.toString().toLowerCase())
		{
		case "false":
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		case "true":
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
		}
	}
}
