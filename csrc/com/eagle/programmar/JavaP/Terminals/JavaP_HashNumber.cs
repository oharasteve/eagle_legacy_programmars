// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class JavaP_HashNumber : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (!genericIdentifier(lines, "#", DIGITS, false, true))
			{
				return false;
			}
			if (_id.length() < 2)
			{
				return false; // Need at least one digit
			}
			return true;
		}
	}

}
