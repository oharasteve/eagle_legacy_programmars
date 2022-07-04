// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColonColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_Use extends TokenSequence
{
	public @S(10) @OPT Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) Rust_Keyword USE = new Rust_Keyword("use");
	public @S(30) SeparatedList<Rust_Identifier_Reference, PunctuationColonColon> useWhat;
	public @S(40) PunctuationSemicolon semicolon;
}
