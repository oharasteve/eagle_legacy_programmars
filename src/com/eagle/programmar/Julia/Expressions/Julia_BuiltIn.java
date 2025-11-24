// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Julia.Terminals.Julia_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Julia_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Julia_KeywordChoice builtinConstant = new Julia_KeywordChoice("false", "true");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString())
		{
		case "false":
			interpreter.pushBool(false);
			break;
		case "true":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinConstant);
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
