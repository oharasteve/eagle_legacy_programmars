// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 22, 2016

namespace com.eagle.programmar.Delphi.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Delphi_Character : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar + 1 >= recLen)
			{
				return false;
			}
			char ch1 = rec.charAt(_currentChar);
			if (ch1 != '#')
			{
				return false;
			}
			char ch2 = rec.charAt(_currentChar + 1);
			if (!char.IsDigit(ch2))
			{
				return false;
			}

			int endChar = _currentChar + 1;
			while (true)
			{
				endChar++;
				if (endChar >= recLen)
				{
					break;
				}
				char ch = rec.charAt(endChar);
				if (!char.IsDigit(ch) && ch != '#')
				{
					break;
				}
			}
			foundIt(_currentLine, endChar - 1);
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "character";
		}
	}

}
