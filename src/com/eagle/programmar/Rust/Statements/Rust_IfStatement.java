// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;

public class Rust_IfStatement extends TokenSequence
{
	public @S(10) @DOC("expressions/if-expr.html") Rust_Keyword IF = new Rust_Keyword("if");
	public @S(20) Rust_Expression condition;
	public @S(30) Rust_Statement stmt;
	public @S(40) @OPT Rust_IfElseClause elseClause;
	
	public static class Rust_IfElseClause extends TokenSequence
	{
		public @S(10) Rust_Keyword ELSE = new Rust_Keyword("else");
		public @S(20) Rust_Statement stmt;
	}
}
