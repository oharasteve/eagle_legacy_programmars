// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Rust_Statement.Rust_Block_Statement;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken.DOC;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_Module {
	public @DOC("items/modules.html") Rust_Keyword MOD = new Rust_Keyword("mod");
	public Rust_Identifier_Definition id;
	public Rust_Module_Body body;
	
	public static class Rust_Module_Body extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		public @CHOICE Rust_Block_Statement stmt;
	}
}
