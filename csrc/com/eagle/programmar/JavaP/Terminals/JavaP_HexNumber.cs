// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class JavaP_HexNumber : TerminalHexNumberToken
	{
		public JavaP_HexNumber() : base("0x", "", false)
		{
		}

		// Probably should go into its own class file
		public class JavaP_HexNoPrefix : TerminalHexNumberToken
		{
			public JavaP_HexNoPrefix() : base("", "", false)
			{
			}
		}
	}

}
