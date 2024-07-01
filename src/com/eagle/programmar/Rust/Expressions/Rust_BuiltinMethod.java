// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_BuiltinMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Rust_KeywordChoice method = new Rust_KeywordChoice("as_str", "len", "rev", "starts_with",
			"to_string");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT Rust_Expression arg;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = method.getValue();
		switch (fnName)
		{
		case "len":
			if (arg.isPresent())
			{
				throw new RuntimeException("The '" + fnName + "' method requires zero arguments");
			}

			String str = interpreter.getStrValue(left);
			interpreter.pushInt(str.length());
			break;
		case "rev":
			if (arg.isPresent())
			{
				throw new RuntimeException("The '" + fnName + "' method requires zero arguments");
			}

			EagleRange range = interpreter.getRangeValue(left);
			interpreter.pushEagleValue(new EagleRange(range._lowValue, range._highValue, -range._step));
			break;
		case "starts_with":
			if (!arg.isPresent())
			{
				throw new RuntimeException("The '" + fnName + "' method requires one argument");
			}
			String text = interpreter.getStrValue(left);
			String patt = interpreter.getStrValue(arg);
			interpreter.pushBool(text.startsWith(patt));
			break;
		case "as_str":
		case "to_string":
			if (arg.isPresent())
			{
				throw new RuntimeException("The '" + fnName + "' method requires zero arguments");
			}

			String val = interpreter.getStrValue(left);
			interpreter.pushStr(val);
			break;
		default:
			throw new RuntimeException("Unable to handle " + fnName);
		}
	}
}
