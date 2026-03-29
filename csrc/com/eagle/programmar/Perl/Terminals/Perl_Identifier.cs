// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public abstract class Perl_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (genericIdentifierWithPrefix(lines, "$#", ALPHAS + "_", ALPHAS + DIGITS + "_"))
			{
				return true;
			}
			return genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_", true, false);
		}
	}

}
