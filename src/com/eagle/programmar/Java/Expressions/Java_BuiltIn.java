// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;

public class Java_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice("false", "true", "null", "this", "String", "super");

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
	
	public static Java_BuiltIn generateExpression(BuiltInEnum builtin, AbstractToken source)
	{
		Java_BuiltIn expr = new Java_BuiltIn();
		switch (builtin)
		{
		case TRUE:
			expr.builtinConstant = new Java_KeywordChoice("true");
			return expr;
		case FALSE:
			expr.builtinConstant = new Java_KeywordChoice("false");
			return expr;
		default:
			throw new RuntimeException("Unable to handle: " + builtin.toString());
		}
	}
}
