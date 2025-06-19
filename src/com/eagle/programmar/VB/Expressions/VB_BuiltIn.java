// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.BuiltInEnum;

public class VB_BuiltIn extends PrimaryOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_KeywordChoice builtIns = new VB_KeywordChoice("false", "true", "nothing");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String builtIn = builtIns.getValue();
		switch (builtIn)
		{
		case "true":
			interpreter.pushBool(true);
			break;
		case "false":
			interpreter.pushBool(false);
			break;
		default:
			throw new RuntimeException("Unable to handle " + builtIn);
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		BuiltInEnum builtIn;
		switch (builtIns.toString())
		{
		case "false":
			builtIn = BuiltInEnum.FALSE;
			break;
		case "true":
			builtIn = BuiltInEnum.TRUE;
			break;
		case "nothing":
			builtIn = BuiltInEnum.NULL;
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtIns);
		}
		return generator.newBuiltInExpression(builtIn, this);
	}
}
