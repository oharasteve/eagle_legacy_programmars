// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural.Terminals
{

	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public abstract class Natural_Identifier : TerminalIdentifierToken
	{
		public static HashSet<string> Natural_keywords = new HashSet<string>();

		public override bool parse(EagleFileReader lines)
		{
			return genericIdentifier(lines, ALPHAS + "#", ALPHAS + DIGITS + "-", true, false);
		}
	}

}
