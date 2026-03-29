// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

namespace com.eagle.programmar.COBOL.Terminals
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalPunctuationChoice = com.eagle.tokens.terminals.TerminalPunctuationChoice;

	public class COBOL_PunctuationChoice : TerminalPunctuationChoice
	{
		// Need default constructor for reading from the XML file
		public COBOL_PunctuationChoice() : base()
		{
		}

		public COBOL_PunctuationChoice(params string[] puncts) : base(puncts)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

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

			return base.parse(lines);
		}
	}
}
