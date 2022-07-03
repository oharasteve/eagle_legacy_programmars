// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_AssignmentStatement extends TokenSequence
{
	public @S(10) Rust_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) Rust_Expression expr;
	public @S(40) @OPT PunctuationSemicolon semicolon;
}
