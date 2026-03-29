// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

namespace com.eagle.programmar.Rust
{
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColonColon = com.eagle.tokens.punctuation.PunctuationColonColon;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Rust_Use : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Rust_Keyword PUB = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("pub");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Keyword USE = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("use");
		public Rust_Keyword USE = new Rust_Keyword("use");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationColonColon> useWhat;
		public SeparatedList<Rust_Identifier_Reference, PunctuationColonColon> useWhat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
