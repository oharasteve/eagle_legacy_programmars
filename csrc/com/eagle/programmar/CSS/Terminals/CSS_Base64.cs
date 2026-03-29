// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

namespace com.eagle.programmar.CSS.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CSS_Base64 : TerminalLiteralToken
	{
		private const string LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar >= recLen)
			{
				return false;
			}

			// Accept anything that looks like a filename
			int endChar = _currentChar;
			while (endChar < recLen)
			{
				char ch = rec.charAt(endChar);
				if (LEGAL_CHARS.IndexOf(ch) < 0)
				{
					break;
				}
				endChar++;
			}
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			foundIt(_currentLine, endChar - 1);
			return true;
		}

		public override string description()
		{
			return "base 64 literal";
		}
	}

}
