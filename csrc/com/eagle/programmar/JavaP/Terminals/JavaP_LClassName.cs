// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class JavaP_LClassName : TerminalLiteralToken
	{
		private const string VALIDS = "/_$"; // Valid characters in the name
		private const string PRIMITIVES = "BIJZ";

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
			char ch = rec.charAt(_currentChar);
			int endChar = _currentChar;

			while (PRIMITIVES.IndexOf(ch) >= 0 && endChar < recLen)
			{
				endChar++;
				ch = rec.charAt(endChar);
			}

			if (ch != 'L')
			{
				return false;
			}
			endChar++;

			while (endChar < recLen)
			{
				ch = rec.charAt(endChar);
				if (!char.IsLetterOrDigit(ch) && VALIDS.IndexOf(ch) < 0)
				{
					// Not a valid classname character
					if (ch != ';' && ch != '<')
					{
						return false; // Must end with a semicolon eventually
					}
					endChar--; // Don't include the ; or <
					break;
				}
				endChar++;
			}
			if (_endChar == _currentChar)
			{
				return false; // Nothing there, just L; or L<
			}

			foundIt(_currentLine, endChar);
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "class name";
		}
	}

}
