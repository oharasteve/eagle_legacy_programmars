// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class SQL_Builtin extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) SQL_KeywordChoice builtIn = new SQL_KeywordChoice(
			"FALSE", "NULL", "SYSTIMESTAMP", "TRUE");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtIn.toString().toUpperCase())
		{
		case "FALSE":
			interpreter.pushBool(false);
			return;
		case "TRUE":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn: " + builtIn);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		BuiltInEnum val;
		switch (builtIn.toString().toUpperCase())
		{
		case "FALSE":
			val = BuiltInEnum.FALSE;
			break;
		case "TRUE":
			val = BuiltInEnum.TRUE;
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtIn);
		}
		return generator.newBuiltInExpression(val, this);
	}
}
