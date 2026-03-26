// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 3, 2026

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_AsExpression extends PrecedenceOperator
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_Keyword AS = new Rust_Keyword("as");
	public @S(30) Rust_Type right = new Rust_Type();
	
	public static Rust_AsExpression generateAsExpr(Rust_Expression expr, Rust_Type type, AbstractToken source)
	{
		Rust_AsExpression as = new Rust_AsExpression();
		as.left = expr;
		as.right = type;

		as.setTransformationSource(source);
		return as;
	}
}
