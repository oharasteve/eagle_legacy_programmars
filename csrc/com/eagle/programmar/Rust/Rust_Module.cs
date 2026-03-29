// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust
{
	using Rust_Block_Statement = com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
	using Rust_Module_Definition = com.eagle.programmar.Rust.Symbols.Rust_Module_Definition;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Rust_Module : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Rust_Keyword PUB = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("pub");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("items/modules.html") com.eagle.programmar.Rust.Terminals.Rust_Keyword MOD = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("mod");
		public @DOC("items/modules.html") Rust_Keyword MOD = new Rust_Keyword("mod");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Symbols.Rust_Module_Definition id;
		public Rust_Module_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Rust_Module_Body body;
		public Rust_Module_Body body;

		public static class Rust_Module_Body extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Block_Statement XXstmt;
			public Rust_Block_Statement XXstmt;
		}
	}

}
