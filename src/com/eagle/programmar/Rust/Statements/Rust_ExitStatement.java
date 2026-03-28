// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 28, 2026

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_ExitStatement extends TokenSequence
{
	public @S(10) @NEWLINE Rust_Keyword PROCESS = new Rust_Keyword("process");
	public @S(20) Rust_Punctuation colonColon = new Rust_Punctuation("::");
	public @S(30) @NEWLINE Rust_Keyword EXIT = new Rust_Keyword("exit");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Rust_Expression exitCode;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static Rust_Statement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		Rust_ExitStatement stmt = new Rust_ExitStatement();
		stmt.leftParen = new PunctuationLeftParen();
		stmt.exitCode = (Rust_Expression) code;
		stmt.rightParen = new PunctuationRightParen();
		stmt.semicolon = new PunctuationSemicolon();
		stmt.setTransformationSource(source);
		return Rust_Generator.wrapStatement(stmt);
	}
}
