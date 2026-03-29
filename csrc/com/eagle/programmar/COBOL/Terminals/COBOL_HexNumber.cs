// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

namespace com.eagle.programmar.COBOL.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class COBOL_HexNumber : TerminalHexNumberToken
	{
		public COBOL_HexNumber() : base("Xx", null, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch = rec.charAt(_currentChar);
			if (ch != 'X' && ch != 'x')
			{
				return false;
			}
			if (_currentChar + 3 >= recLen)
			{
				return false; // Not long enough
			}
			if (rec.charAt(_currentChar + 1) != '"')
			{
				return false;
			}

			int endChar = _currentChar + 2;
			while (true)
			{
				endChar++;
				if (endChar >= recLen)
				{
					break;
				}
				ch = rec.charAt(endChar);
				if (HEX.IndexOf(ch) < 0)
				{
					break;
				}
			}
			if (rec.charAt(endChar) != '"')
			{
				return false;
			}
			foundIt(_currentLine, endChar);
			_numberAsText = rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}
	}

}
