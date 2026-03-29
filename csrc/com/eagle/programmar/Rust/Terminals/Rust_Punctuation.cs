// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Rust_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Rust_Punctuation() : this('\0')
		{
		}

		public Rust_Punctuation(char punct) : base(punct)
		{
		}

		public Rust_Punctuation(string punct) : base(punct)
		{
		}
	}

}
