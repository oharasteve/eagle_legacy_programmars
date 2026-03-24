// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_KeywordChoice builtinConstant = new Rust_KeywordChoice("false", "true");

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

	public static Rust_BuiltIn generateBuiltIn(BuiltInEnum builtin, AbstractToken source)
	{
		Rust_BuiltIn built = new Rust_BuiltIn();
		switch (builtin)
		{
		case TRUE:
			built.builtinConstant.setValue("true");
			break;
		case FALSE:
			built.builtinConstant.setValue("false");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtin);
		}
		built.setTransformationSource(source);
		return built;
	}
}
