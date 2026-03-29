// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class BNF_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "-", true, false);
		}
	}

}
