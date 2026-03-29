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

	public class JavaP_QualifiedName : TerminalLiteralToken
	{
		private const string VALIDS = "/-._$:"; // Valid characters in the name

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

			int endChar = _currentChar;
			while (endChar < recLen)
			{
				char ch = rec.charAt(endChar);
				if (!char.IsLetterOrDigit(ch) && VALIDS.IndexOf(ch) < 0)
				{
					// Not a valid filename character
					break;
				}
				endChar++;
			}

			if (endChar == _currentChar)
			{
				return false; // Zero length -> fail
			}
			foundIt(_currentLine, endChar - 1);
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "qualified name";
		}
	}

}
