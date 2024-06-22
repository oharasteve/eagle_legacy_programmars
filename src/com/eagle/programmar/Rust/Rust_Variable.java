// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

package com.eagle.programmar.Rust;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.ArrayValue;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Rust_Variable extends TokenSequence implements AbstractVariable, EagleRunnable
{
	public @S(10) Rust_Identifier_Reference var;
	public @S(20) @OPT Rust_Subscript subscript;

	public static class Rust_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Rust_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(var.getValue());

		if (subscript.isPresent() && value instanceof ArrayValue)
		{
			int subscr = interpreter.getIntValue(subscript.expr);
			ArrayValue val = (ArrayValue) value;
			interpreter.pushEagleValue(val.getValue(subscr));
		}
		else
		{
			interpreter.pushEagleValue(value);
		}
	}
}
