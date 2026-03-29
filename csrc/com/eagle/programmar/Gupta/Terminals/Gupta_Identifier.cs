// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public abstract class Gupta_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericIdentifier(lines, ALPHAS + DIGITS, ALPHAS + DIGITS + "_", true, false);
		}
	}

}
