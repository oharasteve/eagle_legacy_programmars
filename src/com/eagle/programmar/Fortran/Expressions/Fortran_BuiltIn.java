// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_KeywordChoice builtinConstant = new Fortran_KeywordChoice(".FALSE.", ".TRUE.");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinConstant.toString().toUpperCase())
		{
		case ".FALSE.":
			interpreter.pushBool(false);
			return;
		case ".TRUE.":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		switch (builtinConstant.toString().toUpperCase())
		{
		case ".FALSE.":
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		case ".TRUE.":
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
		}
	}
}
