// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.BuiltInEnum;
import com.eagle.generate.Expressions.Eagle_Generate_BuiltIn;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_BuiltIn<Java_Expression>
{
	public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice(
			"false", "true", "null", "this", "super");

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
		throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
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
		case "null":
			return generator.newBuiltInExpression(BuiltInEnum.NULL, this);
		case "this":
			return generator.newBuiltInExpression(BuiltInEnum.SELF, this);
		case "super":
			return generator.newBuiltInExpression(BuiltInEnum.SUPER, this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtinConstant);
		}
	}

	@Override
	public Java_Expression generateBuiltIn(BuiltInEnum builtin, AbstractToken source)
	{
		switch (builtin)
		{
		case TRUE:
			this.builtinConstant.setValue("true");
			break;
		case FALSE:
			this.builtinConstant.setValue("false");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtin);
		}
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
