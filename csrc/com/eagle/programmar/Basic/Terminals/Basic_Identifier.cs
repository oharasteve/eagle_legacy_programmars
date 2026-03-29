// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class Basic_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			bool ok = genericIdentifier(lines, ALPHAS, DIGITS + "$", true, false);
			if (ok)
			{
				if (_id.length() > 2)
				{
					// Can only have one '$' or digit
					return false;
				}
			}
			return ok;
		}
	}

}
