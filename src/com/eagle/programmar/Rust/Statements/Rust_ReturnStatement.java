// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_ReturnStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("expressions/return-expr.html") Rust_Keyword RETURN = new Rust_Keyword("return");
	public @S(20) Rust_Expression expr;
	public @S(30) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
