// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Rust_Literal extends TerminalLiteralToken
{
	public Rust_Literal()
	{
		super("\"", true, '\\', false, false);
	}
	
	public static Rust_Literal generateLiteral(String value, AbstractToken source)
	{
		Rust_Literal lit = new Rust_Literal();
		String val = '"' + value
				.replaceAll("\\\\n", "\n")
				.replaceAll("\\\"", "\\\"")
				.replaceAll("\\\\", "\\\\\\\\") + '"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public static Rust_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		return Rust_Generator.wrapExpression(generateLiteral(value, source));
	}
}
