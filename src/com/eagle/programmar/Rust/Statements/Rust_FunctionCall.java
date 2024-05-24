// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_FunctionCall extends TokenSequence
{
	public @S(10) Java_Variable methodName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT PunctuationSemicolon semicolon;
}
