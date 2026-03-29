// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Terminals
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class COBOL_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public COBOL_Punctuation() : this('\0')
		{
		}

		public COBOL_Punctuation(char punct) : base(punct)
		{
		}

		public COBOL_Punctuation(string punct) : base(punct)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			if (_punct1 == '*')
			{
				// Make sure a '*' is not a comment
				EagleLineReader rec = lines.get(_currentLine);
				if (rec.charAt(_currentChar) == '*')
				{
					EagleSyntax syntax = this.getSyntax();
					if (_currentChar == syntax._commentColumn)
					{
						return false;
					}
				}
			}
			return base.parse(lines);
		}
	}

}
