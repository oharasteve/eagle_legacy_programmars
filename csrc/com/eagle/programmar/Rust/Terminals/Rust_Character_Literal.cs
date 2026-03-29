// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Rust_Character_Literal : TerminalLiteralToken
	{
		public Rust_Character_Literal() : base("'", true, '\\', false, false)
		{
		}

		public static Rust_Character_Literal generateCharLiteral(string value, AbstractToken source)
		{
			Rust_Character_Literal charLit = new Rust_Character_Literal();
			charLit.setValue(value);
			charLit.setTransformationSource(source);
			return charLit;
		}
	}
}
