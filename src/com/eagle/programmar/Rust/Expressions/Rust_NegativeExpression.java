// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Rust_NegativeExpression extends PrimaryOperator
{
	public @S(10) Rust_Punctuation neg = new Rust_Punctuation("-");
	public @S(20) Rust_Expression expr;
}
