// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_AssignmentStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Rust_Variable var;
	public @S(20) Rust_PunctuationChoice equals = new Rust_PunctuationChoice("=", "+=", "-=");
	public @S(30) Rust_Expression expr;
	public @S(40) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.var.getValue(), val);
	}
}
