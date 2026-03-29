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
	using TerminalPunctuationChoice = com.eagle.tokens.terminals.TerminalPunctuationChoice;

	public class Powershell_PunctuationChoice : TerminalPunctuationChoice
	{
		// Need default constructor for reading from the XML file
		public Powershell_PunctuationChoice() : base()
		{
		}

		public Powershell_PunctuationChoice(params string[] puncts) : base(puncts)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (!base.parse(lines))
			{
				return false;
			}
			return base.dontAllowLettersAfterHyphen(lines);
		}
	}
}
