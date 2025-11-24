// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_BreakStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/loop-expr.html#break-expressions") Rust_Keyword BREAK = new Rust_Keyword("break");
	public @S(20) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newBreakStatement(BREAK);
	}
}
