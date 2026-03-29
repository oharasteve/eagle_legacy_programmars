// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class Rust_Number : TerminalNumberToken
	{
		public Rust_Number() : base("Ee", "LlFfDd", true, true, '_')
		{
		}

		public static Rust_Number createNumber(int value)
		{
			return generateNumber(Convert.ToString(value), null);
		}

		public static Rust_Number generateNumber(string value, AbstractToken source)
		{
			Rust_Number num = new Rust_Number();
			num.setValue(value);
			num.setTransformationSource(source);
			return num;
		}
	}

}
