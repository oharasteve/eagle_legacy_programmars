// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Rust_BuiltIn extends PrimaryOperator
{
	public @S(10) Rust_KeywordChoice builtinConstant = new Rust_KeywordChoice("false", "true");
}
