// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Powershell_KeywordChoice builtIn = new Powershell_KeywordChoice("$False", "$True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtIn.toString().toLowerCase())
		{
		case "$false":
			interpreter.pushBool(false);
			return;
		case "$true":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than $True/$False: " + builtIn);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		BuiltInEnum built;
		switch (builtIn.toString().toLowerCase())
		{
		case "$false":
			built = BuiltInEnum.FALSE;
			break;
		case "$true":
			built = BuiltInEnum.TRUE;
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than $True/$False: " + builtIn);
		}
		return generator.newBuiltInExpression(built, this);
	}
}
