// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Bash.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class Bash_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "_", true, false);
		}
	}

}
