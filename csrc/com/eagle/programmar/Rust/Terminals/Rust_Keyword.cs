// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Rust_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Rust_Keyword() : this("")
		{
		}

		public Rust_Keyword(string word) : base(word)
		{
		}
	}

}
