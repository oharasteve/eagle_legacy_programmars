// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalPictureToken = com.eagle.tokens.terminals.TerminalPictureToken;

	public class COBOL_Picture : TerminalPictureToken
	{
		private const string BRITISH_POUND = "\uFFFD"; // 65533 in decimal. Looks like a cursive L
		private static readonly string FIRST = "9ZXSVBP+-*$" + BRITISH_POUND;
		private static readonly string REST = FIRST + "DCR()012345678/,";

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch1 = char.ToUpper(rec.charAt(_currentChar));
			if (FIRST.IndexOf(ch1) >= 0)
			{
				_endChar = _currentChar + 1;
				int countLeftParen = 0;
				int countRightParen = 0;
				while (_endChar < recLen)
				{
					char ch2 = char.ToUpper(rec.charAt(_endChar));
					if (ch2 == '(')
					{
						countLeftParen++;
					}
					else if (ch2 == ')')
					{
						countRightParen++;
					}

					if (REST.IndexOf(ch2) < 0 && !(ch2 == '.' && _endChar + 1 < recLen && char.IsDigit(rec.charAt(_endChar + 1))))
					{
						break;
					}

					_endChar++;
				}

				if (countLeftParen != countRightParen)
				{
					return false;
				}
				foundIt(_currentLine, _endChar - 1);
				_pic = rec.substring(_currentChar, (_endChar + 1) - _currentChar);
				return true;
			}
			return false;
		}

		public override string description()
		{
			return "A COBOL PICTURE, such 99V99 or X(10)";
		}
	}

}
