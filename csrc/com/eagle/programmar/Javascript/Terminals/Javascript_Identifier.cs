// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public abstract class Javascript_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			// Allow just _ or $ as variable names
			return genericIdentifier(lines, ALPHAS + "_$", ALPHAS + DIGITS + "_$", false, false);
		}
	}

}
