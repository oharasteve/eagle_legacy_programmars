// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_MethodInvocation extends PrimaryOperator
{
	public @S(10) Java_Variable methodName;
	public @S(20) @OPT Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Rust_Expression,PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;
}
