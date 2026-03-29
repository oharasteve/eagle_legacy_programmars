// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

namespace com.eagle.programmar.Python.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class Python_HexNumber : TerminalHexNumberToken
	{
		public Python_HexNumber() : base("0x", "Ll", false)
		{
		}

		public static Python_HexNumber generateHexNumber(string value, AbstractToken source)
		{
			Python_HexNumber hex = new Python_HexNumber();
			hex.setValue(value);
			hex.setTransformationSource(source);
			return hex;
		}
	}

}
