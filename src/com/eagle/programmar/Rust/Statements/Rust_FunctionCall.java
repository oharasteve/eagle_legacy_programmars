// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_FunctionCall extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Rust_MethodInvocation method;
	public @S(20) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(method);
	}
}
