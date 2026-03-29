// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.CMD.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalFilename = com.eagle.tokens.terminals.TerminalFilename;

	public class CMD_Filename : TerminalFilename
	{
		public override bool parse(EagleFileReader lines)
		{
			return genericFilename(lines);
		}
	}

}
