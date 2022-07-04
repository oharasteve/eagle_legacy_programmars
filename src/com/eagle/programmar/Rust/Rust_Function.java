// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Rust_Statement.Rust_Block_Statement;
import com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
import com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_Function extends TokenSequence {
	public @S(10) @OPT Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) Rust_Keyword FN = new Rust_Keyword("fn");
	public @S(30) Rust_Function_Definition id;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT SeparatedList<Rust_Parameter,PunctuationComma> funcParamDefs;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT Rust_FunctionReturns returns;
	public @S(80) Rust_Block_Statement stmt;
	
	public static class Rust_FunctionReturns extends TokenSequence
	{
		public @S(10) Rust_Punctuation arrow = new Rust_Punctuation("->");
		public @S(20) Rust_Type returnType;
	}
	
	public static class Rust_Parameter extends TokenSequence
	{
		public @S(10) Rust_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) Rust_Type type;
	}
}
