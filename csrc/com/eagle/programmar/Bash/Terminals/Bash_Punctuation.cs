// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

namespace com.eagle.programmar.Bash.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Bash_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Bash_Punctuation() : this('\0')
		{
		}

		public Bash_Punctuation(char punct) : base(punct)
		{
		}

		public Bash_Punctuation(string punct) : base(punct)
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
