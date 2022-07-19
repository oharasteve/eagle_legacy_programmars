// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_BreakStatement extends TokenSequence
{
	public @S(10) @DOC("expressions/loop-expr.html#break-expressions") Rust_Keyword BREAK = new Rust_Keyword("break");
	public @S(20) @OPT PunctuationSemicolon semicolon;
}
