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
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Powershell_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Powershell_Punctuation() : this('\0')
		{
		}

		public Powershell_Punctuation(char punct) : base(punct)
		{
		}

		public Powershell_Punctuation(string punct) : base(punct)
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
