// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Rust_SubscriptExpression extends PrecedenceOperator
{
	public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Rust_Expression subscr1;
	public @S(40) @OPT Rust_Punctuation dots = new Rust_Punctuation("..");
	public @S(50) @OPT Rust_Expression subscr2;
	public @S(60) PunctuationRightBracket rightBracket;
}
