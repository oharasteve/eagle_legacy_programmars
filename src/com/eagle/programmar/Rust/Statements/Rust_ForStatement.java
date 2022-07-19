// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;

public class Rust_ForStatement extends TokenSequence
{
	public @S(10) @DOC("expressions/loop-expr.html#iterator-loops") Rust_Keyword FOR = new Rust_Keyword("for");
	public @S(20) Rust_Variable var;
	public @S(30) Rust_Keyword IN = new Rust_Keyword("in");
	public @S(40) Rust_Expression expr;
	public @S(50) Rust_Statement stmt;
}
