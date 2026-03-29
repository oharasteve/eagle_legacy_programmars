// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class Rust_HexNumber : TerminalHexNumberToken
	{
		public Rust_HexNumber() : base("0x", "Ll", true)
		{
		}

		public static Rust_HexNumber generateHexNumber(string value, AbstractToken source)
		{
			Rust_HexNumber num = new Rust_HexNumber();
			num.setValue(value);
			num.setTransformationSource(source);
			return num;
		}
	}

}
