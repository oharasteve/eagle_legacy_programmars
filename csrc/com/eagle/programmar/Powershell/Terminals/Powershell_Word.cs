// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2024

namespace com.eagle.programmar.Powershell.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalWord = com.eagle.tokens.terminals.TerminalWord;

	public class Powershell_Word : TerminalWord
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericWord(lines, ",;(){}[]|");
		}
	}

}
