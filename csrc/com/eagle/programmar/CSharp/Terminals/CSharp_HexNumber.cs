// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.CSharp.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class CSharp_HexNumber : TerminalHexNumberToken
	{
		public CSharp_HexNumber() : base("0x", "LlUu", false)
		{
		}

		public static CSharp_HexNumber generateHexNumber(string value, AbstractToken source)
		{
			CSharp_HexNumber hex = new CSharp_HexNumber();
			hex.setValue(value);
			hex.setTransformationSource(source);
			return hex;
		}
	}

}
