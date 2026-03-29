// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

namespace com.eagle.programmar.IBMASM.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class IBMASM_Label : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "@", true, true);
		}
	}

}
