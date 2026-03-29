// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

namespace com.eagle.programmar.SQL.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class SQL_HexString : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar + 2 >= recLen)
			{
				return false;
			}
			if (rec.charAt(_currentChar) != 'X')
			{
				return false;
			}
			if (rec.charAt(_currentChar + 1) != '\'')
			{
				return false;
			}

			int endChar = _currentChar + 2;
			while (true)
			{
				if (endChar >= recLen)
				{
					return false;
				}
				char ch = rec.charAt(endChar);
				if (TerminalHexNumberToken.HEX.IndexOf(ch) < 0)
				{
					break;
				}
				endChar++;
			}

			if (endChar >= recLen)
			{
				return false;
			}
			if (rec.charAt(endChar) != '\'')
			{
				return false;
			}

			_txt = rec.substring(_currentChar, endChar - _currentChar);
			foundIt(_currentLine, endChar);
			return true;
		}

		public override string description()
		{
			return "hex string";
		}
	}
}
