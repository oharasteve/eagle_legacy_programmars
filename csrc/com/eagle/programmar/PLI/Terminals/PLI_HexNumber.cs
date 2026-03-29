// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

namespace com.eagle.programmar.PLI.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;

	public class PLI_HexNumber : TerminalHexNumberToken
	{
		public PLI_HexNumber() : base("", "Xx", false)
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
			if (ch != '\'')
			{
				return false;
			}

			int endChar = _currentChar + 1;
			while (true)
			{
				if (endChar >= recLen)
				{
					return false;
				}
				ch = char.ToUpper(rec.charAt(endChar));
				if (HEX.IndexOf(ch) < 0)
				{
					break;
				}
				endChar++;
			}

			if (endChar + 1 >= recLen)
			{
				return false;
			}
			if (rec.charAt(endChar) != '\'')
			{
				return false;
			}
			if (!string.ReferenceEquals(char.ToUpper(rec.charAt(endChar + 1)), 'X'))
			{
				return false;
			}

			_numberAsText = rec.substring(_currentChar, endChar - _currentChar);
			foundIt(_currentLine, endChar + 1);
			return true;
		}
	}

}
