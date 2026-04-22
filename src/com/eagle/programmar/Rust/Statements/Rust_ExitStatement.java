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
	public @S(10) @NEWLINE @OPT Rust_ExitStd std;
	public @S(20) @NOSPACE Rust_Keyword PROCESS = new Rust_Keyword("process");
	public @S(30) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
	public @S(40) @NOSPACE Rust_Keyword EXIT = new Rust_Keyword("exit");
	public @S(50) @NOSPACE PunctuationLeftParen leftParen;
	public @S(60) @NOSPACE Rust_Expression exitCode;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;
	public @S(80) @OPT @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_ExitStd extends TokenSequence
	{
		public @S(10) Rust_Keyword STD = new Rust_Keyword("std");
		public @S(20) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
	}

	public static Rust_Statement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		Rust_ExitStd std = new Rust_ExitStd();
		std.setPresent(true);
		
		Rust_ExitStatement stmt = new Rust_ExitStatement();
		stmt.std = std;
		stmt.leftParen = new PunctuationLeftParen();
		stmt.exitCode = (Rust_Expression) code;
		stmt.rightParen = new PunctuationRightParen();
		stmt.semicolon = new PunctuationSemicolon();
		stmt.semicolon.setPresent(true);
		stmt.setTransformationSource(source);
		return Rust_Generator.wrapStatement(stmt);
	}
}
