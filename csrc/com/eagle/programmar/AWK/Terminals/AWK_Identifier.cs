// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class AWK_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_", false, false))
			{
				return true;
			}

			// Check for $1 etc
			if (genericIdentifier(lines, "$", ALPHAS + DIGITS + "_", false, false))
			{
				if (_id.length() > 1)
				{
					return true;
				}
			}

			return false;
		}
	}

}
