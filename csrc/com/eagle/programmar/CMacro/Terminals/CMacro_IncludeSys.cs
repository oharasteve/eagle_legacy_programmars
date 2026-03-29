// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2015

namespace com.eagle.programmar.CMacro.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CMacro_IncludeSys : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			return quotePair(lines, '<', '>');
		}

		public override string description()
		{
			return "include system file";
		}
	}

}
