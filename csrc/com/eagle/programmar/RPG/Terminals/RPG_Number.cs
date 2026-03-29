// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class RPG_Number : TerminalNumberToken
	{
		private int fixedSc, fixedEc;

		// Used by XML Reader ...
		public RPG_Number() : this(0, 0)
		{
		}

		public RPG_Number(int sc, int ec) : base("Ee", null, true, false, '?')
		{
			fixedSc = sc - 1;
			fixedEc = ec;
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			_endChar = rec.length();
			if (_endChar < fixedSc || fixedSc < 0)
			{
				return false; // Too short
			}
			if (_endChar > fixedEc)
			{
				_endChar = fixedEc;
			}
			_numberAsText = rec.substring(fixedSc, _endChar - fixedSc).Trim();
			if (_numberAsText.length() == 0)
			{
				return false; // No number there
			}

			// Make sure it looks like a number
			bool foundDigit = false;
			bool foundDecimalPoint = false;
			for (int i = 0; i < _numberAsText.length(); i++)
			{
				char ch = _numberAsText.charAt(i);
				if (ch == ' ')
				{
					continue;
				}
				if (ch == '.')
				{
					// Only allow one decimal point
					if (foundDecimalPoint)
					{
						return false;
					}
					foundDecimalPoint = true;
				}
				if (DIGITS.IndexOf(ch) < 0)
				{
					return false;
				}
				foundDigit = true;
			}
			if (!foundDigit)
			{
				return false;
			}

			foundIt(_currentLine, _endChar - 1);
			return true;
		}
	}

}
