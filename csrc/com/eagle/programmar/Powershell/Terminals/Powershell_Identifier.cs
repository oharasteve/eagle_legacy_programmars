// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class Powershell_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (!genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_-", false, false))
			{
				return false;
			}
			removeTrailingHyphens();
			return true;
		}

		protected internal virtual void removeTrailingHyphens()
		{
			// Cannot have the last character be a minus sign (-) in an identifier
			while (_id.EndsWith("-"))
			{
				_id = _id.Substring(0, _id.length() - 1); // Toss the - at the end
				_currentChar--;
				_endChar--;
			}
		}
	}

}
